package com.example.demo.service;

import com.example.demo.model.PortfolioHolding;
import com.example.demo.model.RiskAnalysisResult;
import com.example.demo.model.RiskThreshold;
import com.example.demo.model.UserPortfolio;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PortfolioHoldingRepository;
import com.example.demo.repository.RiskAnalysisResultRepository;
import com.example.demo.repository.RiskThresholdRepository;
import com.example.demo.repository.UserPortfolioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RiskAnalysisService {

    private final RiskAnalysisResultRepository resultRepository;
    private final UserPortfolioRepository userPortfolioRepository;
    private final PortfolioHoldingRepository holdingRepository;
    private final RiskThresholdRepository thresholdRepository;

    // constructor signature per spec:
    // (RiskAnalysisResultRepository, UserPortfolioRepository,
    //  PortfolioHoldingRepository, RiskThresholdRepository)
    public RiskAnalysisService(RiskAnalysisResultRepository resultRepository,
                               UserPortfolioRepository userPortfolioRepository,
                               PortfolioHoldingRepository holdingRepository,
                               RiskThresholdRepository thresholdRepository) {
        this.resultRepository = resultRepository;
        this.userPortfolioRepository = userPortfolioRepository;
        this.holdingRepository = holdingRepository;
        this.thresholdRepository = thresholdRepository;
    }

    public RiskAnalysisResult analyzePortfolio(Long portfolioId) {
        UserPortfolio portfolio = userPortfolioRepository.findById(portfolioId)
                .orElseThrow(ResourceNotFoundException::new);
        List<PortfolioHolding> holdings = holdingRepository.findByPortfolio(portfolio);
        RiskThreshold threshold = thresholdRepository.findByActiveTrue()
                .orElseThrow(ResourceNotFoundException::new);

        BigDecimal totalValue = holdings.stream()
                .map(PortfolioHolding::getMarketValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        double highestStockPct = 0.0;
        double highestSectorPct = 0.0;

        if (totalValue.compareTo(BigDecimal.ZERO) > 0) {
            Map<Long, BigDecimal> byStock = holdings.stream()
                    .collect(Collectors.groupingBy(h -> h.getStock().getId(),
                            Collectors.mapping(PortfolioHolding::getMarketValue,
                                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

            for (BigDecimal v : byStock.values()) {
                double pct = v.multiply(BigDecimal.valueOf(100))
                        .divide(totalValue, 4, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                highestStockPct = Math.max(highestStockPct, pct);
            }

            Map<String, BigDecimal> bySector = holdings.stream()
                    .collect(Collectors.groupingBy(h -> h.getStock().getSector(),
                            Collectors.mapping(PortfolioHolding::getMarketValue,
                                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

            for (BigDecimal v : bySector.values()) {
                double pct = v.multiply(BigDecimal.valueOf(100))
                        .divide(totalValue, 4, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                highestSectorPct = Math.max(highestSectorPct, pct);
            }
        }

        boolean highRisk = highestStockPct > threshold.getMaxSingleStockPercentage()
                || highestSectorPct > threshold.getMaxSectorPercentage();

        RiskAnalysisResult result = new RiskAnalysisResult();
        result.setPortfolio(portfolio);
        result.setHighestStockPercentage(highestStockPct);
        result.setHighestSectorPercentage(highestSectorPct);
        result.setIsHighRisk(highRisk);
        result.setNotes(highRisk ? "Portfolio exceeds thresholds" : "Within thresholds");

        return resultRepository.save(result);
    }

    public RiskAnalysisResult getAnalysisById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<RiskAnalysisResult> getAnalysesForPortfolio(Long portfolioId) {
        UserPortfolio portfolio = userPortfolioRepository.findById(portfolioId)
                .orElseThrow(ResourceNotFoundException::new);
        return resultRepository.findByPortfolio(portfolio);
    }
}

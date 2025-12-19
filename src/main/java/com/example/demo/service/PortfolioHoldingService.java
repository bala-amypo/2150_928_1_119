package com.example.demo.service;

import com.example.demo.entity.PortfolioHolding;
import com.example.demo.entity.Stock;
import com.example.demo.entity.UserPortfolio;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PortfolioHoldingRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.UserPortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioHoldingService {

    private final PortfolioHoldingRepository holdingRepository;
    private final UserPortfolioRepository userPortfolioRepository;
    private final StockRepository stockRepository;

    // constructor signature per spec:
    // (PortfolioHoldingRepository, UserPortfolioRepository, StockRepository)
    public PortfolioHoldingService(PortfolioHoldingRepository holdingRepository,
                                   UserPortfolioRepository userPortfolioRepository,
                                   StockRepository stockRepository) {
        this.holdingRepository = holdingRepository;
        this.userPortfolioRepository = userPortfolioRepository;
        this.stockRepository = stockRepository;
    }

    public PortfolioHolding createHolding(PortfolioHolding holding) {
        validateQuantity(holding.getQuantity());
        attachManagedRefs(holding);
        return holdingRepository.save(holding);
    }

    public PortfolioHolding updateHolding(Long id, PortfolioHolding holding) {
        PortfolioHolding existing = getHoldingById(id);
        validateQuantity(holding.getQuantity());
        existing.setQuantity(holding.getQuantity());
        existing.setMarketValue(holding.getMarketValue());
        if (holding.getStock() != null) {
            Stock stock = getStock(holding.getStock().getId());
            existing.setStock(stock);
        }
        if (holding.getPortfolio() != null) {
            UserPortfolio portfolio = getPortfolio(holding.getPortfolio().getId());
            existing.setPortfolio(portfolio);
        }
        return holdingRepository.save(existing);
    }

    public PortfolioHolding getHoldingById(Long id) {
        return holdingRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<PortfolioHolding> getHoldingsByPortfolio(Long portfolioId) {
        UserPortfolio portfolio = getPortfolio(portfolioId);
        return holdingRepository.findByPortfolio(portfolio);
    }

    public void deleteHolding(Long id) {
        holdingRepository.deleteById(id);
    }

    private void validateQuantity(Double quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
    }

    private UserPortfolio getPortfolio(Long id) {
        return userPortfolioRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    private Stock getStock(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void attachManagedRefs(PortfolioHolding holding) {
        if (holding.getPortfolio() != null && holding.getPortfolio().getId() != null) {
            holding.setPortfolio(getPortfolio(holding.getPortfolio().getId()));
        }
        if (holding.getStock() != null && holding.getStock().getId() != null) {
            holding.setStock(getStock(holding.getStock().getId()));
        }
    }
}

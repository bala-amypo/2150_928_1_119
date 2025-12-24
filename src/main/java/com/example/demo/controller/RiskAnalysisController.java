package com.example.demo.controller;

import com.example.demo.model.RiskAnalysisResult;
import com.example.demo.service.RiskAnalysisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-analysis")
@Tag(name = "Risk Analysis")
public class RiskAnalysisController {

    private final RiskAnalysisService riskAnalysisService;

    public RiskAnalysisController(RiskAnalysisService riskAnalysisService) {
        this.riskAnalysisService = riskAnalysisService;
    }

    // POST /analyze/{portfolioId} – Run risk analysis
    @PostMapping("/analyze/{portfolioId}")
    public RiskAnalysisResult analyze(@PathVariable Long portfolioId) {
        return riskAnalysisService.analyzePortfolio(portfolioId);
    }

    // GET /{id} – Get analysis result
    @GetMapping("/{id}")
    public RiskAnalysisResult getById(@PathVariable Long id) {
        return riskAnalysisService.getAnalysisById(id);
    }

    // GET /portfolio/{portfolioId} – List analyses for portfolio
    @GetMapping("/portfolio/{portfolioId}")
    public List<RiskAnalysisResult> getByPortfolio(@PathVariable Long portfolioId) {
        return riskAnalysisService.getAnalysesForPortfolio(portfolioId);
    }
}

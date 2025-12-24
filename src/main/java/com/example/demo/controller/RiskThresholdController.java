package com.example.demo.controller;

import com.example.demo.model.RiskThreshold;
import com.example.demo.service.RiskThresholdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-thresholds")
@Tag(name = "Risk Thresholds")
public class RiskThresholdController {

    private final RiskThresholdService thresholdService;

    public RiskThresholdController(RiskThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    // POST / - Create threshold
    @PostMapping
    public RiskThreshold create(@RequestBody RiskThreshold threshold) {
        return thresholdService.createThreshold(threshold);
    }

    // PUT /{id} - Update threshold
    @PutMapping("/{id}")
    public RiskThreshold update(@PathVariable Long id,
                                @RequestBody RiskThreshold threshold) {
        return thresholdService.updateThreshold(id, threshold);
    }

    // GET /active - Get active threshold
    @GetMapping("/active")
    public RiskThreshold getActive() {
        return thresholdService.getActiveThreshold();
    }

    // GET /{id} - Get threshold by ID
    @GetMapping("/{id}")
    public RiskThreshold getById(@PathVariable Long id) {
        return thresholdService.getThresholdById(id);
    }

    // GET / - List thresholds
    @GetMapping
    public List<RiskThreshold> getAll() {
        return thresholdService.getAllThresholds();
    }
}

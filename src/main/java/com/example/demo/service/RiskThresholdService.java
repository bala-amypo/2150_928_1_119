package com.example.demo.service;

import com.example.demo.model.RiskThreshold;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RiskThresholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskThresholdService {

    private final RiskThresholdRepository thresholdRepository;

    // constructor signature per spec: (RiskThresholdRepository)
    public RiskThresholdService(RiskThresholdRepository thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    public RiskThreshold createThreshold(RiskThreshold threshold) {
        validatePercentages(threshold);
        return thresholdRepository.save(threshold);
    }

    public RiskThreshold updateThreshold(Long id, RiskThreshold threshold) {
        RiskThreshold existing = getThresholdById(id);
        existing.setThresholdName(threshold.getThresholdName());
        existing.setMaxSingleStockPercentage(threshold.getMaxSingleStockPercentage());
        existing.setMaxSectorPercentage(threshold.getMaxSectorPercentage());
        existing.setActive(threshold.getActive());
        validatePercentages(existing);
        return thresholdRepository.save(existing);
    }

    public RiskThreshold getActiveThreshold() {
        return thresholdRepository.findByActiveTrue()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public RiskThreshold getThresholdById(Long id) {
        return thresholdRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<RiskThreshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }

    private void validatePercentages(RiskThreshold t) {
        if (!isValidPercentage(t.getMaxSingleStockPercentage())
                || !isValidPercentage(t.getMaxSectorPercentage())) {
            throw new IllegalArgumentException("Percentages must be between 0 and 100");
        }
    }

    private boolean isValidPercentage(Double value) {
        return value != null && value >= 0 && value <= 100;
    }
}

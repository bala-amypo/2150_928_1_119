package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "risk_thresholds")
public class RiskThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String thresholdName;

    @Column(nullable = false)
    private Double maxSingleStockPercentage;

    @Column(nullable = false)
    private Double maxSectorPercentage;

    @Column(nullable = false)
    private Boolean active = true;

    public RiskThreshold() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThresholdName() {
        return thresholdName;
    }

    public void setThresholdName(String thresholdName) {
        this.thresholdName = thresholdName;
    }

    public Double getMaxSingleStockPercentage() {
        return maxSingleStockPercentage;
    }

    public void setMaxSingleStockPercentage(Double maxSingleStockPercentage) {
        this.maxSingleStockPercentage = maxSingleStockPercentage;
    }

    public Double getMaxSectorPercentage() {
        return maxSectorPercentage;
    }

    public void setMaxSectorPercentage(Double maxSectorPercentage) {
        this.maxSectorPercentage = maxSectorPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

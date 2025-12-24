package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "risk_analysis_results")
public class RiskAnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserPortfolio portfolio;

    @Column(nullable = false)
    private Instant analysisDate = Instant.now();

    @Column(nullable = false)
    private Double highestStockPercentage;

    @Column(nullable = false)
    private Double highestSectorPercentage;

    @Column(nullable = false)
    private Boolean isHighRisk;

    @Column(length = 500)
    private String notes;

    public RiskAnalysisResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(UserPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Instant getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(Instant analysisDate) {
        this.analysisDate = analysisDate;
    }

    public Double getHighestStockPercentage() {
        return highestStockPercentage;
    }

    public void setHighestStockPercentage(Double highestStockPercentage) {
        this.highestStockPercentage = highestStockPercentage;
    }

    public Double getHighestSectorPercentage() {
        return highestSectorPercentage;
    }

    public void setHighestSectorPercentage(Double highestSectorPercentage) {
        this.highestSectorPercentage = highestSectorPercentage;
    }

    public Boolean getIsHighRisk() {
        return isHighRisk;
    }

    public void setIsHighRisk(Boolean highRisk) {
        isHighRisk = highRisk;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

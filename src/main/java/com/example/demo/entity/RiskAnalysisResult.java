// RiskAnalysisResult.java
package com.example.demo.entity;

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

    public RiskAnalysisResult() {}

    // getters and setters
}

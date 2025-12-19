// RiskThreshold.java
package com.example.demo.entity;

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

    public RiskThreshold() {}

    // getters and setters
}

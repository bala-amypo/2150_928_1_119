// PortfolioHolding.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "portfolio_holdings")
public class PortfolioHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserPortfolio portfolio;

    @ManyToOne(optional = false)
    private Stock stock;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private BigDecimal marketValue;

    @Column(nullable = false)
    private Instant lastUpdated = Instant.now();

    public PortfolioHolding() {}

    // getters and setters
}

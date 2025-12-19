// UserPortfolio.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_portfolios",
       uniqueConstraints = @UniqueConstraint(columnNames = {"userId","portfolioName"}))
public class UserPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(nullable = false)
    private String portfolioName;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @Column(nullable = false)
    private Boolean active = true;

    @PrePersist
    public void onCreate() { this.createdAt = Instant.now(); }

    @PreUpdate
    public void onUpdate() { this.updatedAt = Instant.now(); }

    public UserPortfolio() {}

    // getters and setters
}

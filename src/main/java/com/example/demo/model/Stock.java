package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticker;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private Boolean active = true;

    public Stock() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTicker() { return ticker; }

    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getSector() { return sector; }

    public void setSector(String sector) { this.sector = sector; }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }
}

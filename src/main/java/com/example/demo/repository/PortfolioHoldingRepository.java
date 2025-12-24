// PortfolioHoldingRepository.java
package com.example.demo.repository;

import com.example.demo.model.PortfolioHolding;
import com.example.demo.model.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioHoldingRepository extends JpaRepository<PortfolioHolding, Long> {

    List<PortfolioHolding> findByPortfolio(UserPortfolio portfolio);
}

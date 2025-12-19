// UserPortfolioRepository.java
package com.example.demo.repository;

import com.example.demo.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {
    // required by spec
    List<UserPortfolio> findByUserId(Long userId);
}

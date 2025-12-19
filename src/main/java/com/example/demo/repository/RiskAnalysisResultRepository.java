// RiskAnalysisResultRepository.java
package com.example.demo.repository;

import com.example.demo.entity.RiskAnalysisResult;
import com.example.demo.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskAnalysisResultRepository extends JpaRepository<RiskAnalysisResult, Long> {

    List<RiskAnalysisResult> findByPortfolio(UserPortfolio portfolio);
}

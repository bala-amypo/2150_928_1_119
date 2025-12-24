// RiskAnalysisResultRepository.java
package com.example.demo.repository;

import com.example.demo.model.RiskAnalysisResult;
import com.example.demo.model.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskAnalysisResultRepository extends JpaRepository<RiskAnalysisResult, Long> {

    List<RiskAnalysisResult> findByPortfolio(UserPortfolio portfolio);
}

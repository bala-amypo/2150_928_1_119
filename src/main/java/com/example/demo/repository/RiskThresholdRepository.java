// RiskThresholdRepository.java
package com.example.demo.repository;

import com.example.demo.entity.RiskThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskThresholdRepository extends JpaRepository<RiskThreshold, Long> {
    // required by spec
    Optional<RiskThreshold> findByActiveTrue();
}

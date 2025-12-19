// StockRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    // required by spec
    Optional<Stock> findByTicker(String ticker);
}

package com.example.demo.service;

import com.example.demo.entity.Stock;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository; // constructor injection

    // constructor signature per spec: (StockRepository)
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock createStock(Stock stock) {
        stockRepository.findByTicker(stock.getTicker())
                .ifPresent(s -> { throw new IllegalArgumentException("Duplicate ticker"); });
        stock.setActive(true);
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long id, Stock stock) {
        Stock existing = getStockById(id);
        existing.setCompanyName(stock.getCompanyName());
        existing.setSector(stock.getSector());
        return stockRepository.save(existing);
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock deactivateStock(Long id) {
        Stock existing = getStockById(id);
        existing.setActive(false);
        return stockRepository.save(existing);
    }
}

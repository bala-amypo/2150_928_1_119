package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock createStock(Stock stock) {
        stockRepository.findByTicker(stock.getTicker())
                .ifPresent(s -> { throw new IllegalArgumentException("Duplicate ticker"); });
        stock.setActive(true);
        return stockRepository.save(stock);
    }

    // Test expects: updateStock(long, Stock)
    @Transactional
    public Stock updateStock(long id, Stock stock) {
        Stock existing = stockRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        existing.setTicker(stock.getTicker());
        existing.setCompanyName(stock.getCompanyName());
        existing.setSector(stock.getSector());
        existing.setActive(stock.getActive());
        return stockRepository.save(existing);
    }

    // Test expects: getStockById(long)
    @Transactional(readOnly = true)
    public Stock getStockById(long id) {
        return stockRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    // Test expects: getAllStocks()
    @Transactional(readOnly = true)
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Test expects: deactivateStock(long)
    @Transactional
    public void deactivateStock(long id) {
        Stock existing = getStockById(id);
        existing.setActive(false);
        stockRepository.save(existing);
    }

    // Optional: keep your companyName-based methods if you still need them.
}

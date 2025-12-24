package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.exception.ResourceNotFoundException;
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

    // update using companyName instead of id
    @Transactional
    public Stock updateStockByCompanyName(String companyName, Stock stock) {
        Stock existing = stockRepository.findByCompanyName(companyName)
                .orElseThrow(ResourceNotFoundException::new);

        existing.setCompanyName(stock.getCompanyName());
        existing.setSector(stock.getSector());
        return stockRepository.save(existing);
    }

    // deactivate using companyName
    @Transactional
    public Stock deactivateStockByCompanyName(String companyName) {
        Stock existing = stockRepository.findByCompanyName(companyName)
                .orElseThrow(ResourceNotFoundException::new);
        existing.setActive(false);
        return stockRepository.save(existing);
    }

    @Transactional(readOnly = true)
    public Stock getStockByCompanyName(String companyName) {
        return stockRepository.findByCompanyName(companyName)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}

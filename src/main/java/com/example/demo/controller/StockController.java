package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stocks")
public class StockController {

    private final StockService stockService; // ctor injection

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    // Update by companyName
    @PutMapping("/company/{companyName}")
    public Stock update(@PathVariable String companyName, @RequestBody Stock stock) {
        return stockService.updateStockByCompanyName(companyName, stock);
    }

    // Get by companyName
    @GetMapping("/company/{companyName}")
    public Stock getByCompanyName(@PathVariable String companyName) {
        return stockService.getStockByCompanyName(companyName);
    }

    // Get all
    @GetMapping
    public List<Stock> getAll() {
        return stockService.getAllStocks();
    }

    // Deactivate by companyName
    @PutMapping("/company/{companyName}/deactivate")
    public Stock deactivate(@PathVariable String companyName) {
        return stockService.deactivateStockByCompanyName(companyName);
    }
}

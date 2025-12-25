package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Stocks")
public class StockController {

    private final StockService stockService; // ctor injection

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // Test expects: createStock(Stock)
    @PostMapping("/stocks")
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    // Test expects: updateStock(long, Stock)
    @PutMapping("/stocks/{id}")
    public Stock updateStock(@PathVariable long id, @RequestBody Stock stock) {
        return stockService.updateStock(id, stock);
    }

    // Test expects: getStock(long)
    @GetMapping("/stocks/{id}")
    public Stock getStock(@PathVariable long id) {
        return stockService.getStockById(id);
    }

    // Test expects: getAllStocks()
    @GetMapping("/stocks")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    // Test expects: deactivateStock(long)
    @PutMapping("/stocks/{id}/deactivate")
    public void deactivateStock(@PathVariable long id) {
        stockService.deactivateStock(id);
    }

    // You can optionally keep your companyName endpoints as extra methods.
}

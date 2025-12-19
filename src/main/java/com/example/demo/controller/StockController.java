// src/main/java/com/example/demo/controller/StockController.java
package com.example.demo.controller;

import com.example.demo.entity.Stock;
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

    @PutMapping("/{id}")
    public Stock update(@PathVariable Long id, @RequestBody Stock stock) {
        return stockService.updateStock(id, stock);
    }

    @GetMapping("/{id}")
    public Stock getById(@PathVariable Long id) {
        return stockService.getStockById(id);
    }

    @GetMapping
    public List<Stock> getAll() {
        return stockService.getAllStocks();
    }

    @PutMapping("/{id}/deactivate")
    public Stock deactivate(@PathVariable Long id) {
        return stockService.deactivateStock(id);
    }
}

package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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

    // Test expects: createStock(Stock) -> ResponseEntity<Stock>
    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.createStock(stock));
    }

    // Test expects: updateStock(long, Stock) -> ResponseEntity<Stock>
    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable long id,
                                             @RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.updateStock(id, stock));
    }

    // Test expects: getStock(long) -> ResponseEntity<Stock>
    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    // Test expects: getAllStocks() -> ResponseEntity<List<Stock>>
    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    // Test expects: deactivateStock(long) -> ResponseEntity<Void>
    @PutMapping("/stocks/{id}/deactivate")
    public ResponseEntity<Void> deactivateStock(@PathVariable long id) {
        stockService.deactivateStock(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: you can re-add companyName-based endpoints if you need them
}

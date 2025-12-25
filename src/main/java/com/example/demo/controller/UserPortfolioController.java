package com.example.demo.controller;

import com.example.demo.model.UserPortfolio;
import com.example.demo.service.UserPortfolioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Portfolios")
public class UserPortfolioController {

    private final UserPortfolioService portfolioService;

    public UserPortfolioController(UserPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/portfolios")
    public ResponseEntity<UserPortfolio> createPortfolio(@RequestBody UserPortfolio portfolio) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolio));
    }

    // ✅ FIXED: Test calls this - must use getPortfolioById to throw RuntimeException
    @GetMapping("/portfolios/{id}")
    public ResponseEntity<UserPortfolio> getPortfolio(@PathVariable long id) {
        return ResponseEntity.ok(portfolioService.getPortfolioById(id));
    }

    @PutMapping("/portfolios/{id}")
    public UserPortfolio update(@PathVariable Long id, @RequestBody UserPortfolio portfolio) {
        return portfolioService.updatePortfolio(id, portfolio);
    }

    @GetMapping("/portfolios/user/{userId}")
    public List<UserPortfolio> getByUser(@PathVariable Long userId) {
        return portfolioService.getPortfoliosByUser(userId);
    }

    @PutMapping("/portfolios/{id}/deactivate")
    public UserPortfolio deactivate(@PathVariable Long id) {
        return portfolioService.deactivatePortfolio(id);
    }
}

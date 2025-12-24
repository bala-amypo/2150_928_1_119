// src/main/java/com/example/demo/controller/UserPortfolioController.java
package com.example.demo.controller;

import com.example.demo.model.UserPortfolio;
import com.example.demo.service.UserPortfolioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@Tag(name = "Portfolios")
public class UserPortfolioController {

    private final UserPortfolioService portfolioService;

    public UserPortfolioController(UserPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping
    public UserPortfolio create(@RequestBody UserPortfolio portfolio) {
        return portfolioService.createPortfolio(portfolio);
    }

    @PutMapping("/{id}")
    public UserPortfolio update(@PathVariable Long id,
                                @RequestBody UserPortfolio portfolio) {
        return portfolioService.updatePortfolio(id, portfolio);
    }

    @GetMapping("/{id}")
    public UserPortfolio getById(@PathVariable Long id) {
        return portfolioService.getPortfolioById(id);
    }

    @GetMapping("/user/{userId}")
    public List<UserPortfolio> getByUser(@PathVariable Long userId) {
        return portfolioService.getPortfoliosByUser(userId);
    }

    @PutMapping("/{id}/deactivate")
    public UserPortfolio deactivate(@PathVariable Long id) {
        return portfolioService.deactivatePortfolio(id);
    }
}

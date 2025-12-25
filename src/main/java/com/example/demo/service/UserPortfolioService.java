package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserPortfolio;
import com.example.demo.repository.UserPortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPortfolioService {

    private final UserPortfolioRepository userPortfolioRepository;

    public UserPortfolioService(UserPortfolioRepository userPortfolioRepository) {
        this.userPortfolioRepository = userPortfolioRepository;
    }

    // Test expects: createPortfolio(UserPortfolio)
    public UserPortfolio createPortfolio(UserPortfolio portfolio) {
        portfolio.setActive(true);
        return userPortfolioRepository.save(portfolio);
    }

    // Test expects: getPortfolio(long)
    public UserPortfolio getPortfolio(long id) {
        return userPortfolioRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    // Existing methods
    public UserPortfolio updatePortfolio(Long id, UserPortfolio portfolio) {
        UserPortfolio existing = getPortfolio(id);
        existing.setPortfolioName(portfolio.getPortfolioName());
        return userPortfolioRepository.save(existing);
    }

    public List<UserPortfolio> getPortfoliosByUser(Long userId) {
        return userPortfolioRepository.findByUserId(userId);
    }

    public UserPortfolio deactivatePortfolio(Long id) {
        UserPortfolio existing = getPortfolio(id);
        existing.setActive(false);
        return userPortfolioRepository.save(existing);
    }
}

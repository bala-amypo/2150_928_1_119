package com.example.demo.service;

import com.example.demo.model.UserPortfolio;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserPortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPortfolioService {

    private final UserPortfolioRepository userPortfolioRepository;

    // constructor signature per spec: (UserPortfolioRepository)
    public UserPortfolioService(UserPortfolioRepository userPortfolioRepository) {
        this.userPortfolioRepository = userPortfolioRepository;
    }

    public UserPortfolio createPortfolio(UserPortfolio portfolio) {
        portfolio.setActive(true);
        return userPortfolioRepository.save(portfolio);
    }

    public UserPortfolio updatePortfolio(Long id, UserPortfolio portfolio) {
        UserPortfolio existing = getPortfolioById(id);
        existing.setPortfolioName(portfolio.getPortfolioName());
        return userPortfolioRepository.save(existing);
    }

    public UserPortfolio getPortfolioById(Long id) {
        return userPortfolioRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<UserPortfolio> getPortfoliosByUser(Long userId) {
        return userPortfolioRepository.findByUserId(userId);
    }

    public UserPortfolio deactivatePortfolio(Long id) {
        UserPortfolio existing = getPortfolioById(id);
        existing.setActive(false);
        return userPortfolioRepository.save(existing);
    }
}

package com.codegym.case_study_m4.service.Wallet;

import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.repository.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService implements IWalletService {
    @Autowired
    private IWalletRepository walletRepository;

    @Override
    public Iterable<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Page<Wallet> findAll(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public void removeById(Long id) {
        walletRepository.deleteById(id);
    }

    @Override
    public Iterable<Wallet> findWalletByNameContaining(String name) {
        return walletRepository.findByNameContaining(name);
    }
}

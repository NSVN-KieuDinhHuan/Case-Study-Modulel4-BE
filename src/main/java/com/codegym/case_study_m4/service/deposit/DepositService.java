package com.codegym.case_study_m4.service.deposit;

import com.codegym.case_study_m4.model.Deposit;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService implements IDepositService{
    @Autowired
    private IDepositRepository depositRepository;

    @Override
    public Page<Deposit> findAll(Pageable pageable) {
        return depositRepository.findAll(pageable);
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById(id);
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public void removeById(Long id) {
        depositRepository.deleteById(id);
    }

    @Override
    public Iterable<Deposit> findAllByWallet(Wallet wallet) {
        return depositRepository.findAllByWallet(wallet);
    }
}

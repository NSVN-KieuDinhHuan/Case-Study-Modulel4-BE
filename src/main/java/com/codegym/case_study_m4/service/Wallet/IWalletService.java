package com.codegym.case_study_m4.service.Wallet;

import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.service.IGeneralService;

import java.util.Optional;


public interface IWalletService extends IGeneralService<Wallet> {

    Iterable<Wallet> findAll();

    Iterable<Wallet> findWalletByNameContaining(String s);

    void deleteWallet(Long id);

    Optional<Wallet> findById(Long id);

    Iterable<Wallet> findAllByUser(User user);

}

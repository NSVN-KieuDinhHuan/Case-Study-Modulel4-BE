package com.codegym.case_study_m4.service.Wallet;

import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWalletService extends IGeneralService<Wallet> {

    Page<Wallet> findWalletByNameContaining(String s);
    Page<Wallet> findAll(Pageable pageable);

    Page<Wallet> findWalletByNameContaining(String name, Pageable pageable);
}

package com.codegym.case_study_m4.service.Wallet;

import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.service.IGeneralService;

public interface IWalletService extends IGeneralService<Wallet> {

    Iterable<Wallet> findWalletByNameContaining(String s);
}

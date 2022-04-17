package com.codegym.case_study_m4.service.deposit;

import com.codegym.case_study_m4.model.Deposit;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.service.IGeneralService;

public interface IDepositService extends IGeneralService<Deposit> {

    Iterable<Deposit> findAllByWallet(Wallet wallet);
}

package com.codegym.case_study_m4.service.deposit;

import com.codegym.case_study_m4.model.Deposit;
import com.codegym.case_study_m4.service.IGeneralService;


public interface IDepositService extends IGeneralService<Deposit> {
    Iterable<Deposit> findAllDepositByUser(Long inputUser_id);

    Iterable<Deposit> findAllDepositByUserAndTime(Long inputUser_id, String startDate, String endDate);

    Iterable<Deposit> findAllDepositByWallet(Long inputWallet_id);

    Iterable<Deposit> findAllDepositByWalletAndTime(Long inputWallet_id, String startDate, String endDate);
}

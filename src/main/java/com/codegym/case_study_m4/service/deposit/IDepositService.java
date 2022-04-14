package com.codegym.case_study_m4.service.deposit;

import com.codegym.case_study_m4.model.Deposit;
import com.codegym.case_study_m4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepositService extends IGeneralService<Deposit> {
    Page<Deposit> findAllDepositByUser(Long user_id, Pageable pageable);
}

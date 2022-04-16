package com.codegym.case_study_m4.service.payment;

import com.codegym.case_study_m4.model.Payment;
import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPaymentService extends IGeneralService<Payment> {
    Iterable<Payment> findPaymentByUser(Long user_id);
}

package com.codegym.case_study_m4.service.payment;

import com.codegym.case_study_m4.model.Payment;
import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IPaymentService extends IGeneralService<Payment> {
    Iterable<Payment> findPaymentByUser(Long user_id);
    Iterable<Payment> findPaymentByUserAndDate(Long user_id, Date startDate, Date endDate);
    Iterable<Payment> findAllByWallet(Wallet wallet);

}

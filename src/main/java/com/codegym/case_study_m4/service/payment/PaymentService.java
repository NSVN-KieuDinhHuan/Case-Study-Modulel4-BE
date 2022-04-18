package com.codegym.case_study_m4.service.payment;

import com.codegym.case_study_m4.model.Payment;
import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void removeById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Iterable<Payment> findPaymentByUser(Long user_id) {
        return paymentRepository.findPaymentByUser(user_id);
    }

    @Override
    public Iterable<Payment> findPaymentByUserAndDate(Long user_id, Date startDate, Date endDate) {
        return paymentRepository.findPaymentByUserAndDate(user_id,startDate,endDate);
    }

    @Override
    public Iterable<Payment> findAllByWallet(Wallet wallet) {
        return paymentRepository.findAllByWallet(wallet);
    }
}

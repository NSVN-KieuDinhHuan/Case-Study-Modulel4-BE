package com.codegym.case_study_m4.service.paymentCategory;

import com.codegym.case_study_m4.model.PaymentCategory;
import com.codegym.case_study_m4.repository.IPaymentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCategoryService implements IPaymentCategoryService {
    @Autowired
    private IPaymentCategoryRepository paymentCategoryRepository;

    @Override
    public Page<PaymentCategory> findAll(Pageable pageable) {
        return paymentCategoryRepository.findAll(pageable);
    }

    @Override
    public Optional<PaymentCategory> findById(Long id) {
        return paymentCategoryRepository.findById(id);
    }

    @Override
    public PaymentCategory save(PaymentCategory paymentCategory) {
        return paymentCategoryRepository.save(paymentCategory);
    }

    @Override
    public void removeById(Long id) {
        paymentCategoryRepository.deleteById(id);
    }

    @Override
    public Iterable<PaymentCategory> findAllCategory() {
        return paymentCategoryRepository.findAll();
    }
}

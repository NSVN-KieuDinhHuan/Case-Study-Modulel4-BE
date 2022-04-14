package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.PaymentCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentCategoryRepository extends PagingAndSortingRepository<PaymentCategory, Long> {
}

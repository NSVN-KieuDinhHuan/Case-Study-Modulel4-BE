package com.codegym.case_study_m4.service.paymentCategory;

import com.codegym.case_study_m4.model.PaymentCategory;
import com.codegym.case_study_m4.service.IGeneralService;

public interface IPaymentCategoryService extends IGeneralService<PaymentCategory> {
    Iterable<PaymentCategory> findAllCategory();
}

package com.codegym.case_study_m4.controller;

import com.codegym.case_study_m4.model.PaymentCategory;
import com.codegym.case_study_m4.service.Wallet.IWalletService;
import com.codegym.case_study_m4.service.paymentCategory.IPaymentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/paymentCategories")
public class PaymentCategoryController {
    @Autowired
    private IPaymentCategoryService paymentCategoryService;
    @Autowired
    private IWalletService walletService;

    @GetMapping
    public ResponseEntity<Iterable<PaymentCategory>>getAllPaymentCategory(){
        return new ResponseEntity<>(paymentCategoryService.findAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentCategory>findById(@PathVariable Long id){
        Optional<PaymentCategory>paymentCategoryOptional = paymentCategoryService.findById(id);
        return new ResponseEntity<>(paymentCategoryOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentCategory> save(@RequestBody PaymentCategory paymentCategory){
        PaymentCategory paymentCategory1 = new PaymentCategory(paymentCategory.getId(),paymentCategory.getName());

        return new ResponseEntity<>(paymentCategoryService.save(paymentCategory1),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentCategory> updatePaymentCategory(@PathVariable Long id, @RequestBody PaymentCategory newPaymentCategory) {
        Optional<PaymentCategory> paymentCategoryOptional = paymentCategoryService.findById(id);
        if (!paymentCategoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newPaymentCategory.setId(id);
        return new ResponseEntity<>(paymentCategoryService.save(newPaymentCategory), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentCategory> deletePaymentCategory(@PathVariable Long id){
        Optional<PaymentCategory> paymentCategoryOptional = paymentCategoryService.findById(id);
        if (!paymentCategoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentCategoryService.removeById(id);
        return new ResponseEntity<>(paymentCategoryOptional.get(),HttpStatus.OK);
    }
}

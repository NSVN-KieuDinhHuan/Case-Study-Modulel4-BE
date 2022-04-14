package com.codegym.case_study_m4.controller;

import com.codegym.case_study_m4.model.Payment;
import com.codegym.case_study_m4.model.dto.PaymentForm;
import com.codegym.case_study_m4.service.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;
    @Value("${file-upload}")
    private String uploadPath;
    @GetMapping
    public ResponseEntity<Page<Payment>> showAllPayment(@RequestParam(defaultValue = "0") Integer page){
        PageRequest pageable = PageRequest.of(page,3);
        Page<Payment> payments = paymentService.findAll(pageable);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Payment> findPaymentByID(@PathVariable Long id){
        Optional<Payment> payment = paymentService.findById(id);
        if(!payment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(payment.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment){
//        MultipartFile multipartFile = paymentForm.getImage();
//        String fileName = multipartFile.getOriginalFilename();
//        fileName = System.currentTimeMillis() + fileName;
//        try {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Payment payment = new Payment(paymentForm.getId(), paymentForm.getAmount(), paymentForm.getDate(), fileName, paymentForm.getPaymentCategory(),paymentForm.getWallet());
        return new ResponseEntity<>(paymentService.save(payment),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Payment> editPayment(@PathVariable Long id, @RequestBody Payment payment){
        Optional<Payment> payment1 = paymentService.findById(id);
        if(!payment1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        payment.setId(id);
        return new ResponseEntity<>(payment,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> deletePayment(@PathVariable Long id){
        Optional<Payment> payment = paymentService.findById(id);
        if(!payment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentService.removeById(id);
        return new ResponseEntity<>(payment.get(),HttpStatus.OK);
    }
}

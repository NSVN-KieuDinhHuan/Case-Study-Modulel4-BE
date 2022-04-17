package com.codegym.case_study_m4.controller;

import com.codegym.case_study_m4.model.Payment;
import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.model.dto.PaymentForm;
import com.codegym.case_study_m4.service.Wallet.IWalletService;
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
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private IWalletService walletService;
    @Value("${file-upload}")
    private String uploadPath;
    @GetMapping
    public ResponseEntity<Page<Payment>> showAllPayment(@RequestParam(defaultValue = "0") Integer page){
        PageRequest pageable = PageRequest.of(page,3);
        Page<Payment> payments = paymentService.findAll(pageable);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    @GetMapping("/user/{user_id}")
    public ResponseEntity<Iterable<Payment>> findAllPaymentByUser(@PathVariable Long user_id, @RequestParam Optional<Date> startDate, Optional<Date> endDate){
//        PageRequest pageable = PageRequest.of(page,3);
        Iterable<Payment> payments = paymentService.findPaymentByUser(user_id);
        if(startDate.isPresent() & endDate.isPresent()){
            payments = paymentService.findPaymentByUserAndDate(user_id,startDate.get(),endDate.get());
        }
        return new ResponseEntity<>(payments,HttpStatus.OK);
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
    public ResponseEntity<Payment> create(@ModelAttribute PaymentForm paymentForm){
        MultipartFile multipartFile = paymentForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        fileName = System.currentTimeMillis() + fileName;
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Payment payment = new Payment(paymentForm.getId(), paymentForm.getAmount(), paymentForm.getDate(), fileName, paymentForm.getPaymentCategory(),paymentForm.getWallet());
//        Payment newPayment = paymentService.save(payment);
        Wallet wallet = (walletService.findById(payment.getWallet().getId())).get();
        Double currentAmount = wallet.getCurrentAmount();
        Double newAmount = currentAmount - payment.getAmount();
        wallet.setCurrentAmount(newAmount);
        walletService.save(wallet);
        return new ResponseEntity<>(paymentService.save(payment),HttpStatus.CREATED);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Payment> editPayment(@PathVariable Long id, @ModelAttribute PaymentForm paymentForm){
        Optional<Payment> oldPayment = paymentService.findById(id);
        if(!oldPayment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = paymentForm.getImage();
        String fileName;
        if(multipartFile == null){
            fileName = oldPayment.get().getImage();
        } else {
            fileName = multipartFile.getOriginalFilename();
            fileName = System.currentTimeMillis() + fileName;
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Payment newPayment = new Payment(id, paymentForm.getAmount(), paymentForm.getDate(),fileName,paymentForm.getPaymentCategory(), paymentForm.getWallet());
        Wallet wallet = newPayment.getWallet();
        Double newAmount = wallet.getCurrentAmount() - oldPayment.get().getAmount() + newPayment.getAmount();
        wallet.setCurrentAmount(newAmount);
        walletService.save(wallet);
        return new ResponseEntity<>(paymentService.save(newPayment),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> deletePayment(@PathVariable Long id){
        Optional<Payment> payment = paymentService.findById(id);
        if(!payment.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentService.removeById(id);
        Wallet wallet = payment.get().getWallet();
        Double newAmount = wallet.getCurrentAmount() + payment.get().getAmount();
        wallet.setCurrentAmount(newAmount);
        walletService.save(wallet);
        return new ResponseEntity<>(payment.get(),HttpStatus.OK);
    }
}

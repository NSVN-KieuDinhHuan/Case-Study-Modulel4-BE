package com.codegym.case_study_m4.controller;


import com.codegym.case_study_m4.model.Wallet;
import com.codegym.case_study_m4.model.WalletForm;
import com.codegym.case_study_m4.service.Wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private IWalletService walletService;

    @Value("${file-upload}")
    private String uploadPath;


    @GetMapping
    public ResponseEntity<Page<Wallet>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault Pageable pageable) {
        Page<Wallet> wallets = walletService.findAll(pageable);
        if (q.isPresent()) {
            wallets = walletService.findWalletByNameContaining(q.get());
        }
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findById(@PathVariable Long id) {
        Optional<Wallet> productOptional = walletService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wallet> save(@ModelAttribute WalletForm walletForm) {
        MultipartFile icon = walletForm.getIcon();
        if (icon.getSize() != 0) {
            String fileName = walletForm.getIcon().getOriginalFilename();
            try {
                FileCopyUtils.copy(walletForm.getIcon().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Wallet wallet = new Wallet(walletForm.getId(), walletForm.getName(), fileName, walletForm.getCurrentAmount(), walletForm.getUser());
            return new ResponseEntity<>(walletService.save(wallet), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateProduct(@PathVariable Long id, @RequestBody Wallet newWallet) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newWallet.setId(id);
        return new ResponseEntity<>(walletService.save(newWallet), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wallet> deleteProduct(@PathVariable Long id) {
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletService.removeById(id);
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.OK);
    }
}

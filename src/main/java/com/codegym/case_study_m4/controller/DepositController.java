package com.codegym.case_study_m4.controller;

import com.codegym.case_study_m4.model.Deposit;
import com.codegym.case_study_m4.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/deposits")
public class DepositController {

    @Autowired
    private IDepositService depositService;

    @GetMapping
    public ResponseEntity<Page<Deposit>> findAll(@PageableDefault (value = 5) Pageable pageable){
        Page<Deposit> deposits = depositService.findAll(pageable);
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<Deposit>> findAllDepositByUser(@PathVariable Long id, @PageableDefault (value = 5) Pageable pageable) {
        Page<Deposit> deposits = depositService.findAllDepositByUser(id, pageable);
        return new ResponseEntity<>(deposits, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Deposit> saveDeposit(@RequestBody Deposit deposit) {
        return new ResponseEntity<>(depositService.save(deposit), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> findDepositById(@PathVariable Long id) {
        Optional<Deposit> depositOptional = depositService.findById(id);
        if (!depositOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(depositOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable Long id, @RequestBody Deposit newDeposit) {
        Optional<Deposit> depositOptional = depositService.findById(id);
        if (!depositOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newDeposit.setId(id);
        return new ResponseEntity<>(depositService.save(newDeposit), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Deposit> deleteDeposit(@PathVariable Long id) {
        Optional<Deposit> depositOptional = depositService.findById(id);
        if (!depositOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        depositService.removeById(id);
        return new ResponseEntity<>(depositOptional.get(), HttpStatus.OK);
    }

}

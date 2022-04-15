package com.codegym.case_study_m4.controller;

import com.codegym.case_study_m4.model.Deposit;
import com.codegym.case_study_m4.model.PaymentCategory;
import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.repository.IUserRepository;
import com.codegym.case_study_m4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<Page<User>> findAllUser(@PageableDefault(value = 0) Pageable pageable){
        Page<User> users = userService.findAllByRoles("ROLE_USER",pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }




}

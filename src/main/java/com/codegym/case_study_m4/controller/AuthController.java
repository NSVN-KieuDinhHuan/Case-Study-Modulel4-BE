package com.codegym.case_study_m4.controller;

import com.codegym.case_study_m4.model.dto.JwtResponse;
import com.codegym.case_study_m4.model.Role;
import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.dto.SignUpForm;
import com.codegym.case_study_m4.service.JwtService;
import com.codegym.case_study_m4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        //Kiểm tra username và pass có đúng hay không
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        //Lưu user đang đăng nhập vào trong context của security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByName(user.getName());
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody SignUpForm user) {
        if (!user.getPasswordForm().getPassword().equals(user.getPasswordForm().getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user1 = new User(user.getName(), user.getPasswordForm().getPassword());
        return new ResponseEntity<>(userService.save(user1), HttpStatus.CREATED);
    }
}

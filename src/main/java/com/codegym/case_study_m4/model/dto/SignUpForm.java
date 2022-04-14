package com.codegym.case_study_m4.model.dto;

import com.codegym.case_study_m4.validator.PasswordConfirm;
import com.codegym.case_study_m4.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotEmpty
    @Size(min=5,max=12)
    @UniqueUsername()
    private String name;
    @Email
    private String email;
    @PasswordConfirm
    private PasswordForm passwordForm;

}

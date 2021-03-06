package com.codegym.case_study_m4.validator;

import com.codegym.case_study_m4.model.dto.PasswordForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<PasswordConfirm, PasswordForm> {
    @Override
    public boolean isValid(PasswordForm passwordForm, ConstraintValidatorContext context) {
        return passwordForm.getPassword().equals(passwordForm.getConfirmPassword());
    }
}

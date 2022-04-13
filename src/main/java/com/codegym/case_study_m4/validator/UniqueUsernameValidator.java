package com.codegym.case_study_m4.validator;

import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.repository.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
   private IUserRepository userRepository;

    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user=userRepository.findByName(username);
        if (user!=null) {
            return false;
        }
        return true;
    }
}

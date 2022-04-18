package com.codegym.case_study_m4.service.user;

import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService  extends IGeneralService<User>, UserDetailsService {
    User findByName(String name);
    Page<User> findAllByRoles(String role, Pageable pageable);
}

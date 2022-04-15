package com.codegym.case_study_m4.service.user;

import com.codegym.case_study_m4.model.Role;
import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.dto.UserPrincipal;
import com.codegym.case_study_m4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        user.setPassword(encodePassword);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(2L, "ROLE_USER"));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        return UserPrincipal.build(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Page<User> findAllByRoles(String role, Pageable pageable) {
        return userRepository.findAllByRoles(role,pageable);
    }


}

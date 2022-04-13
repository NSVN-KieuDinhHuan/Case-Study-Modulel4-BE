package com.codegym.case_study_m4.service.user;

import com.codegym.case_study_m4.model.User;
import com.codegym.case_study_m4.model.UserPrincipal;
import com.codegym.case_study_m4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
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
}

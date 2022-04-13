package com.codegym.case_study_m4.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long id;

    private String name;

    private String password;
    private String image;
    private List<? extends GrantedAuthority> roles;
    private List<Wallet> wallets;

    public static UserPrincipal build(User user) {
        List<Role> roles = user.getRoles(); //Lấy ra role của user
        List<GrantedAuthority> authorities = new ArrayList<>(); //tạo một list quyền cho user principal
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName())); //thêm quyền vào list
        }
        return new UserPrincipal(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getImage(),
                authorities,
                user.getWallets()
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
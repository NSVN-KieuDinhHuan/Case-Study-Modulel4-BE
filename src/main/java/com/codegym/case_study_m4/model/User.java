package com.codegym.case_study_m4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(12)", nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> roles;

    public User(String name,  String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

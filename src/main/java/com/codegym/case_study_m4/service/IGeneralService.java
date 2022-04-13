package com.codegym.case_study_m4.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void removeById(Long id);

}

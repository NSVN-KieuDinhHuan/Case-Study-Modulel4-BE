package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IWalletRepository extends PagingAndSortingRepository<Wallet, Long> {
    Iterable<Wallet> findByNameContaining(String name);

}

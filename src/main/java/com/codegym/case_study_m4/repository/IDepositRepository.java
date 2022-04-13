package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepositRepository extends JpaRepository<Deposit, Long> {
}

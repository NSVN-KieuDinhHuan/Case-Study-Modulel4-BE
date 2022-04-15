package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPaymentRepository extends JpaRepository<Payment,Long> {
//    @Modifying
    @Query(value = "select * from payment join wallets on payment.wallet_id = wallets.id where user_id = 1", nativeQuery = true)
    Iterable<Payment> findPaymentByUser(Long user_id);

//    @Modifying
//    @Query(value = "select * from payment join wallets where user_id = ?1", nativeQuery = true)
//    Page<Payment> findPaymentByUser(Long user_id, Pageable pageable);

}

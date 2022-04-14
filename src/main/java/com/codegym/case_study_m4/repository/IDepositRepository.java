package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IDepositRepository extends JpaRepository<Deposit, Long> {
    @Query(value = "select user.id, deposit.amount, deposit.date from user join wallets on user.id = wallets.user_id join deposit on wallets.id = deposit.wallet_id where user.id = ?1 date(deposit.date) = ?2", nativeQuery = true)
    Iterable<Deposit> findAllDepositInOneDay(Long id, String date);

    @Query(value = "select user.id, deposit.amount, deposit.date from user join wallets on user.id = wallets.user_id join deposit on wallets.id = deposit.wallet_id where user.id = ?1 and date(deposit.date) in (?2, ?3)", nativeQuery = true)
    Iterable<Deposit> findAllDepositBetweenTime(Long id, String startDate, String endDate);

    @Query(value = "select user.id, deposit.amount, deposit.date from user join wallets on user.id = wallets.user_id join deposit on wallets.id = deposit.wallet_id where user.id = ?1 and wallets.id = ?2  and date(deposit.date) in (?3, ?4)", nativeQuery = true)
    Iterable<Deposit> findAllDepositInOneWalletBetweenTime(Long user_id, Long wallet_id, String startDate, String endDate);

    @Query(value = "call findAllDepositByUser(?1)", nativeQuery = true)
    Page<Deposit> findAllDepositByUser(Long inputUser_id, Pageable pageable);
}

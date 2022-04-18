package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.Deposit;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IDepositRepository extends JpaRepository<Deposit, Long> {
    @Query(value = "select deposit.id, deposit.amount, deposit.date, deposit.note, deposit.wallet_id from deposit join wallets on deposit.wallet_id = wallets.id where wallets.user_id = ?1 order by deposit.date", nativeQuery = true)
    Iterable<Deposit> findAllDepositByUser(Long inputUser_id);

    @Query(value = "select deposit.id, deposit.amount, deposit.date, deposit.note, deposit.wallet_id from deposit join wallets on deposit.wallet_id = wallets.id where wallets.user_id = ?1 and (date(deposit.date) between (?2) and (?3)) order by deposit.date", nativeQuery = true)
    Iterable<Deposit> findAllDepositByUserAndTime(Long inputUser_id, String startDate, String endDate);

    @Query(value = "select deposit.id, deposit.amount, deposit.date, deposit.note, deposit.wallet_id from deposit join wallets on deposit.wallet_id = wallets.id where wallets.id = ?1 order by deposit.date ", nativeQuery = true)
    Iterable<Deposit> findAllDepositByWallet(Long inputWallet_id);

    @Query(value = "select deposit.id, deposit.amount, deposit.date, deposit.note, deposit.wallet_id from deposit join wallets on deposit.wallet_id = wallets.id where wallets.id = ?1 and (date(deposit.date) between (?2) and (?3) order by deposit.date;", nativeQuery = true)
    Iterable<Deposit> findAllDepositByWalletAndTime(Long inputWallet_id, String startDate, String endDate);
}

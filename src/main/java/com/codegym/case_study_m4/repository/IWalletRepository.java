package com.codegym.case_study_m4.repository;

import com.codegym.case_study_m4.model.Wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IWalletRepository extends PagingAndSortingRepository<Wallet, Long> {
    Iterable<Wallet> findByNameContaining(String name);

    @Modifying
    @Query(value = "call delete_wallet(?1)", nativeQuery = true)
    void deleteWallet(Long id);

    @Query(value = "select * from wallets where user_id = ?1", nativeQuery = true)
    Iterable<Wallet> findAllWalletByUser(Long user_id);

}

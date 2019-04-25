package com.sti.bootcamp.WalletProject.repository;

import com.sti.bootcamp.WalletProject.model.WalletAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletAccountRepository extends CrudRepository <WalletAccount, Integer> {

    @Query(value = "SELECT w.* FROM walletaccount as w WHERE accountnumber= :accountnumber", nativeQuery = true)
    List<WalletAccount> getwallet(@Param("accountnumber") String accountnumber);

    @Query("FROM WalletAccount WHERE cif.cif= :cif")
    List<WalletAccount> getListAccount(@Param("cif")String cif);
}
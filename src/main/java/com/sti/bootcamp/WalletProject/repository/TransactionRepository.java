package com.sti.bootcamp.WalletProject.repository;

import com.sti.bootcamp.WalletProject.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository <Transaction, Integer> {

    @Query("FROM Transaction WHERE cif.cif= :cif")
    List<Transaction> getListAccount(@Param("cif") String cif);
}

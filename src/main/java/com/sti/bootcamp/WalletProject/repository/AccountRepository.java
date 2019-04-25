package com.sti.bootcamp.WalletProject.repository;

import com.sti.bootcamp.WalletProject.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository <Account, String> {

    @Query("FROM Account WHERE cif.cif= :cif")
    List<Account> getListAccount(@Param("cif") String cif);

}

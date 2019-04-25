package com.sti.bootcamp.WalletProject.dao;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.model.Account;

import java.util.List;

public interface AccountDao {

    List<Account> getList();
    Account addAccount(Account account) throws NotFoundException;
    Account update(Account account) throws NotFoundException;
    Account getById(String acn);
    Account delete(Account account);
    List<Account> getlist(String cif);
    Account credit(String acn, float amount);
    Account debit(String acn, float amount);



}
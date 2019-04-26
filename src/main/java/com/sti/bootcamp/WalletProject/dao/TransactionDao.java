package com.sti.bootcamp.WalletProject.dao;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.model.Transaction;

import java.util.List;

public interface TransactionDao {

    Transaction saveTrans(Transaction transaction);

    List<Transaction> getlist(String cif);

    Transaction topup(Transaction transaction) throws NotFoundException;

    Transaction transfer(Transaction transaction) throws NotFoundException;

    Transaction withdrawal(Transaction transaction) throws NotFoundException;

}

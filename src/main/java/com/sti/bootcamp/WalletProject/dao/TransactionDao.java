package com.sti.bootcamp.WalletProject.dao;

import com.sti.bootcamp.WalletProject.model.Transaction;

import java.util.List;

public interface TransactionDao {

    Transaction saveTrans(Transaction transaction);

    List<Transaction> getlist(String cif);

}

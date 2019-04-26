package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.dao.TransactionDao;
import com.sti.bootcamp.WalletProject.model.Transaction;
import com.sti.bootcamp.WalletProject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private TransactionRepository tr;

    @Override
    public Transaction saveTrans(Transaction transaction) {
        Transaction saveTransaction = tr.save(transaction);
        return saveTransaction;
    }

    @Override
    public List<Transaction> getlist(String cif) {
        return tr.getListAccount(cif);
    }

    @Override
    public Transaction topup(Transaction transaction) throws NotFoundException {
        return null;
    }

    @Override
    public Transaction transfer(Transaction transaction) throws NotFoundException {
        return null;
    }

    @Override
    public Transaction withdrawal(Transaction transaction) throws NotFoundException {
        return null;
    }
}
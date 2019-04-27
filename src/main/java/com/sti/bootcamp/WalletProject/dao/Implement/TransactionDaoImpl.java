package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.config.UserException;
import com.sti.bootcamp.WalletProject.dao.TransactionDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.model.Transaction;
import com.sti.bootcamp.WalletProject.model.TransactionType;
import com.sti.bootcamp.WalletProject.repository.AccountRepository;
import com.sti.bootcamp.WalletProject.repository.TransactionRepository;
import com.sti.bootcamp.WalletProject.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionRepository tr;

    @Autowired
    private AccountRepository ar;

    @Autowired
    private TransactionTypeRepository ttr;

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
    public Transaction topup(Transaction transaction) {
        Account acc = ar.findById(transaction.getAccountNumberCredit()).orElse(null);
        acc.setBalance(acc.getBalance()+transaction.getAmount());
        ar.save(acc);
        transaction.setTransactionType("TOPUP001");
        return tr.save(transaction);
    }

    @Override
    public Transaction transfer(Transaction transaction) {
        Account credit = ar.findById(transaction.getAccountNumberCredit()).orElse(null);
        Account debit = ar.findById(transaction.getAccountNumberDebit()).orElse(null);
        credit.setBalance(credit.getBalance()+transaction.getAmount());
        debit.setBalance(debit.getBalance()-transaction.getAmount());
        transaction.setTransactionType("TRANS001");
        return tr.save(transaction);
    }

    @Override
    public Transaction withdrawal(Transaction transaction) {
        Account acc = ar.findById(transaction.getAccountNumberDebit()).orElse(null);
        acc.setBalance(acc.getBalance()-transaction.getAmount());
        ar.save(acc);
        transaction.setTransactionType("WTHDL001");
        return tr.save(transaction);
    }
}
package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.dao.AccountDao;
import com.sti.bootcamp.WalletProject.dao.TransactionDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.model.Transaction;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/search-transaction/{cif}")
    public CommonResponse<List<Transaction>> getList(@PathVariable (name = "cif") String cif){
        List<Transaction> trclist = transactionDao.getlist(cif);
        CommonResponse<List<Transaction>> cr = new CommonResponse<>();

        if(trclist != null){
            cr.setData(trclist);
        } else {
            cr.setResponseCode("99");
            cr.setResponeMassage("transactionlist not found");
            cr.setData(null);
        }

        return cr;
    }

    @PostMapping("/transaction")
    public CommonResponse<Transaction> transfer(@RequestBody Transaction transaction) throws NotFoundException {
        CommonResponse<Transaction> commonResponse = new CommonResponse<Transaction>();
        String credit = transaction.getAccountNumberCredit();
        float amountCredit = transaction.getAmount();
        String debit = transaction.getAccountNumberDebit();
        float amountDebit = transaction.getAmount();
        String codeTransaction = transaction.getTransactionType();
        if (codeTransaction == "TOPUP001"){
            creditAccount(credit,amountCredit);
            commonResponse.setData(transactionDao.saveTrans(transaction));
        } else if (codeTransaction == "TRANS001"){
            creditAccount(credit,amountCredit);
            debitAccount(debit,amountDebit);
            commonResponse.setData(transactionDao.saveTrans(transaction));
        } else if (codeTransaction == "WTHDL001"){
            debitAccount(debit,amountDebit);
            commonResponse.setData(transactionDao.saveTrans(transaction));
        } else {
            throw new NotFoundException("500","Exception found");
        }
        return commonResponse;
    }

    public CommonResponse<Account> creditAccount(String accountNumberCredit, Float amountCredit) throws NotFoundException{
        CommonResponse<Account> resp = new CommonResponse<>();
        String accountNumber = accountNumberCredit;
        resp.setData(accountDao.credit(accountNumber, amountCredit));
        return resp;
    }

    public CommonResponse<Account> debitAccount(String accountNumberDebit, Float amountDebit) throws NotFoundException{
        CommonResponse<Account> resp = new CommonResponse<>();
        String accountNumber = accountNumberDebit;
        resp.setData(accountDao.debit(accountNumber, amountDebit));
        return resp;
    }

}

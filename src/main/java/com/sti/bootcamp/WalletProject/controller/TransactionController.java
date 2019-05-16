package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.dao.TransactionDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.model.Transaction;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import com.sti.bootcamp.WalletProject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountRepository ar;

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

    @PostMapping("/topup")
    public CommonResponse<Transaction> topup(@RequestBody Transaction transaction) {
        CommonResponse<Transaction> comResp = new CommonResponse<>();
        if(transaction.getAmount() < 25000){
            comResp.setResponseCode("99");
            comResp.setResponeMassage("Amount is less than Rp. 20,000.00");
            return comResp;
        } else if (transaction.getAmount() == 0){
            comResp.setResponseCode("96");
            comResp.setResponeMassage("Please input your amount");
            return comResp;
        } else {
            Transaction tr = transactionDao.topup(transaction);
            comResp.setData(tr);
        }
        return comResp;
    }

    @PostMapping("/transfer")
    public CommonResponse<Transaction> transfer(@RequestBody Transaction transaction) {
        Account debit = ar.findById(transaction.getAccountNumberDebit()).orElse(null);
        Account credit = ar.findById(transaction.getAccountNumberCredit()).orElse(null);

        CommonResponse<Transaction> comResp = new CommonResponse<>();
        if(debit.getBalance() <= 100000){
            comResp.setResponseCode("66");
            comResp.setResponeMassage("Your balance is less than Rp. 100,000.00");
            return comResp;
        } else if (transaction.getAmount() >= debit.getBalance() ) {
            comResp.setResponseCode("99");
            comResp.setResponeMassage("Your amount is less than Balance");
            return comResp;
        } else if (transaction.getAmount() == 0){
            comResp.setResponseCode("96");
            comResp.setResponeMassage("Please input your amount");
            return comResp;
        } else if (credit == null){
            comResp.setResponseCode("69");
            comResp.setResponeMassage("Reciepent does not exist");
            return comResp;
        } else if (credit == debit){
            comResp.setResponseCode("20");
            comResp.setResponeMassage("You can\'t transfer to the same account");
            return comResp;
        } else {
            Transaction tr = transactionDao.transfer(transaction);
            comResp.setData(tr);
        }
        return comResp;
    }

    @PostMapping("/withdrawal")
    public CommonResponse<Transaction> withdrawal(@RequestBody Transaction transaction) {
        Account acc = ar.findById(transaction.getAccountNumberDebit()).orElse(null);
        CommonResponse<Transaction> comResp = new CommonResponse<>();
        if(acc.getBalance() <= 100000){
            comResp.setResponseCode("69");
            comResp.setResponeMassage("Your balance is less than Rp. 100,000.00");
            return comResp;
        } else if (transaction.getAmount() >= acc.getBalance() ) {
            comResp.setResponseCode("96");
            comResp.setResponeMassage("Amount is less than Balance");
            return comResp;
        } else if (transaction.getAmount() < 50000) {
            comResp.setResponseCode("66");
            comResp.setResponeMassage("Amount is less than 50000");
            return comResp;
        } else if (transaction.getAmount() == 0){
            comResp.setResponseCode("99");
            comResp.setResponeMassage("Please input your amount");
            return comResp;
        } else {
            Transaction tr = transactionDao.withdrawal(transaction);
            comResp.setData(tr);
        }
        return comResp;
    }



}

package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.dao.TransactionTypeDao;
import com.sti.bootcamp.WalletProject.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionTypeController {

    @Autowired
    private TransactionTypeDao transactionTypeDao;

    @GetMapping("/transactiontypes")
    public List<TransactionType> getList() {
        List<TransactionType> getList = transactionTypeDao.getlist();
        return getList;
    }

}

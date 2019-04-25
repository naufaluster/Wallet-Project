package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.dao.WalletDao;
import com.sti.bootcamp.WalletProject.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class WalletController {

    @Autowired
    private WalletDao walletDao;

    @GetMapping("/wallets")
    public List<Wallet> getList(){
        List<Wallet> getList = walletDao.getList();
        return getList;
    }

}
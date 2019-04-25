package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.dao.AccountDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/accounts")
    public CommonResponse<List<Account>> getList() {
        CommonResponse<List<Account>> resp = new CommonResponse<>();
        List<Account> getList = accountDao.getList();
        if(getList != null){
            resp.setData(getList);
        } else {
            resp.setResponseCode("99");
            resp.setResponeMassage("account list is not found");
            resp.setData(null);
        }
        return resp;
    }

    @Transactional
    @PostMapping("/register-account")
    public CommonResponse<Account> registerCustomer(@RequestBody Account account) throws NotFoundException {
        CommonResponse<Account> commonResponse = new CommonResponse<>();
        Account acc = accountDao.addAccount(account);
        if(acc != null){
            commonResponse.setData(acc);
        } else {
            commonResponse.setResponseCode("99");
            commonResponse.setResponeMassage("add account failed");
            commonResponse.setData(null);
        }
        return commonResponse;
    }

    @GetMapping("/account/{acn}")
    public CommonResponse<Account> getById(@PathVariable (name = "acn") String acn){
        Account acc = accountDao.getById(acn);
        CommonResponse<Account> comResp = new CommonResponse<Account>();

        if(acc != null){
            comResp.setData(acc);
        } else {
            comResp.setResponseCode("99");
            comResp.setResponeMassage("account not found");
            comResp.setData(null);
        }
        return comResp;
    }

    @GetMapping("/search-account/{cif}")
    public CommonResponse<List<Account>> getList(@PathVariable (name = "cif") String cif){
        List<Account> acclist = accountDao.getlist(cif);
        CommonResponse<List<Account>> cr = new CommonResponse<>();

        if(acclist != null){
            cr.setData(acclist);
        } else {
            cr.setResponseCode("99");
            cr.setResponeMassage("accountlist not found");
            cr.setData(null);
        }

        return cr;
    }

    @PutMapping("/update-account")
    public CommonResponse<Account> update(@RequestBody Account account) throws NotFoundException {
        CommonResponse<Account> comResp = new CommonResponse<Account>();
        Account acc = accountDao.update(account);
        if(acc != null){
            comResp.setData(acc);
        } else {
            comResp.setResponseCode("99");
            comResp.setResponeMassage("update account failed");
            comResp.setData(null);
        }

        return comResp;
    }

    @Transactional
    @DeleteMapping("/delete-account/{acn}")
    public CommonResponse<Account> delete(@PathVariable(name = "acn") String acn){
        CommonResponse<Account> comResp = new CommonResponse<Account>();
        Account acc = accountDao.getById(acn);
        comResp.setData(accountDao.delete(acc));
        return comResp;
    }



}

package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.dao.WalletAccountDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.model.WalletAccount;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import com.sti.bootcamp.WalletProject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
public class WalletAccountController {

    @Autowired
    private WalletAccountDao walletAccountDao;

    @Autowired
    private AccountRepository ar;

    @GetMapping("/walletaccounts")
    public List<WalletAccount> getList(){
        List<WalletAccount> getList = walletAccountDao.getlist();
        return getList;
    }

    @PostMapping("/walletaccount-post")
    public CommonResponse<WalletAccount> registerCustomer(@RequestBody WalletAccount walletAccount){
        CommonResponse<WalletAccount> commonResponse = new CommonResponse<WalletAccount>();
        WalletAccount walletacc = walletAccountDao.addWallet(walletAccount);
        if(walletacc != null){
            commonResponse.setData(walletacc);
        } else {
            commonResponse.setResponseCode("99");
            commonResponse.setResponeMassage("input wallet account failed");
            commonResponse.setData(null);
        }
        return commonResponse;
    }

    @GetMapping("/search-wallets/{accountnumber}")
    public List<WalletAccount> getList(@PathVariable (name = "accountnumber") String accountnumber){
        List<WalletAccount> walletlist = walletAccountDao.getListWallet(accountnumber);
        return walletlist;
    }

    @DeleteMapping("/delete-wallet/{id}")
    public CommonResponse<WalletAccount> delete(@PathVariable(name = "id") int id){
        CommonResponse<WalletAccount> comResp = new CommonResponse<>();
        WalletAccount wallacc = walletAccountDao.delete(id);
        comResp.setData(wallacc);
        return comResp;
    }

    @GetMapping("/search-wallet/{cif}")
    public CommonResponse<List<WalletAccount>> walletList(@PathVariable (name = "cif") String cif){
        List<WalletAccount> walletacc = walletAccountDao.getlistbyCif(cif);
        CommonResponse<List<WalletAccount>> cr = new CommonResponse<>();

        if(walletacc != null){
            cr.setData(walletacc);
        } else {
            cr.setResponseCode("99");
            cr.setResponeMassage("walletlist not found");
            cr.setData(null);
        }

        return cr;
    }

    @PostMapping("/wallet-topup")
    public CommonResponse<WalletAccount> withdrawal(@RequestBody WalletAccount walletAccount) {
        Account acc = ar.findById(walletAccount.getAccountnumber()).orElse(null);
        CommonResponse<WalletAccount> comResp = new CommonResponse<>();
        if(acc.getBalance() <= 100000){
            comResp.setResponseCode("69");
            comResp.setResponeMassage("Your balance is less than Rp. 100,000.00");
            return comResp;
        } else if (walletAccount.getAmount() >= acc.getBalance() ) {
            comResp.setResponseCode("96");
            comResp.setResponeMassage("Amount is less than Balance");
            return comResp;
        } else if (walletAccount.getAmount() < 25000) {
            comResp.setResponseCode("66");
            comResp.setResponeMassage("Amount is less than 25000");
            return comResp;
        } else if (walletAccount.getAmount() == 0){
            comResp.setResponseCode("99");
            comResp.setResponeMassage("Please input your amount");
            return comResp;
        } else {
            WalletAccount tu = walletAccountDao.topup(walletAccount);
            comResp.setData(tu);
        }
        return comResp;
    }

}

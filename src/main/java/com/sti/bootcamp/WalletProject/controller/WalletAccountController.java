package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.dao.WalletAccountDao;
import com.sti.bootcamp.WalletProject.model.WalletAccount;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class WalletAccountController {

    @Autowired
    private WalletAccountDao walletAccountDao;

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

    @GetMapping("/search-wallet/{accountnumber}")
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

    @GetMapping("/search-walletaccount/{cif}")
    public List<WalletAccount> getListwallet(@PathVariable (name = "cif") String cif){
        List<WalletAccount> walletacclist = walletAccountDao.getlistbyCif(cif);
        return walletacclist;
    }

}

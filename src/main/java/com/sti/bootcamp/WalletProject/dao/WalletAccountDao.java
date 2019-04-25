package com.sti.bootcamp.WalletProject.dao;

import com.sti.bootcamp.WalletProject.model.WalletAccount;

import java.util.List;

public interface WalletAccountDao {

    List<WalletAccount> getlist();

    WalletAccount addWallet(WalletAccount walletAccount);

    List<WalletAccount> getListWallet(String accountnumber);

    WalletAccount delete(int id);

    List<WalletAccount> getlistbyCif(String cif);

}

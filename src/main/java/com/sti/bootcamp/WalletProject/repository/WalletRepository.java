package com.sti.bootcamp.WalletProject.repository;

import com.sti.bootcamp.WalletProject.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository <Wallet, String>  {
}

package com.sti.bootcamp.WalletProject.repository;

import com.sti.bootcamp.WalletProject.model.TransactionType;
import org.springframework.data.repository.CrudRepository;

public interface TransactionTypeRepository extends CrudRepository <TransactionType, String> {
}

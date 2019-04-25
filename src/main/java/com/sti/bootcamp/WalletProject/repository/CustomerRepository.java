package com.sti.bootcamp.WalletProject.repository;

import com.sti.bootcamp.WalletProject.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, String> {

    Customer findByUsername(String username);

}

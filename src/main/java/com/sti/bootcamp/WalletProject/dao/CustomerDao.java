package com.sti.bootcamp.WalletProject.dao;

import com.sti.bootcamp.WalletProject.exception.NotFoundException;
import com.sti.bootcamp.WalletProject.exception.UserException;
import com.sti.bootcamp.WalletProject.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> getList() ;
    Customer getById(String cif) throws NotFoundException;
    Customer register(Customer customer) throws NotFoundException;
    Customer login(Customer customer) throws NotFoundException;
    Customer update(Customer customer) throws NotFoundException;
    Customer getUname(String username) throws UserException;
    Customer getUsername(String username) throws UserException;

}

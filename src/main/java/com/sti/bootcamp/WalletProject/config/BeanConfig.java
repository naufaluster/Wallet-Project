package com.sti.bootcamp.WalletProject.config;

import com.sti.bootcamp.WalletProject.dao.*;
import com.sti.bootcamp.WalletProject.dao.Implement.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CustomerDao customerDao(){
        return new CustomerDaoImpl();
    }

    @Bean
    public AccountDao accountDao(){
        return  new AccountDaoImpl();
    }

    @Bean
    public WalletDao walletDao(){
        return new WalletDaoImpl();
    }

    @Bean
    public WalletAccountDao walletAccountDao(){
        return new WalletAccountDaoImpl();
    }

    @Bean
    public TransactionDao transactionDao(){
        return new TransactionDaoImpl();
    }

    @Bean TransactionTypeDao transactionTypeDao(){
        return new TransactionTypeDaoImpl();
    }

}

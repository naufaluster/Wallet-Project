package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.dao.AccountDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public List<Account> getList() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = builder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        query.select(root);

        Query q = em.createQuery(query);

        return q.getResultList();
    }

    @Override
    public Account addAccount(Account account) throws NotFoundException {
        String acn="";
        Query query = em.createQuery("FROM Account order by accountnumber desc");
        query.setMaxResults(1);
        if(query.getResultList().isEmpty()){
            acn = "ACN-0001";
        } else {
            Account accounts = (Account) query.getSingleResult();
            int acnn =accounts.getAccountNumber().length();
            String tid= accounts.getAccountNumber().substring(5,acnn);
            acn="ACN-"+String.format("%04d", Integer.parseInt(tid) +1);
        }
        account.setAccountNumber(acn);
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account update(Account account) throws NotFoundException {
        return accountRepository.save(account);
    }

    @Override
    public Account getById(String acn) {
        return em.find(Account.class, acn);
    }

    @Override
    public Account delete(Account account) {
        em.remove(account);
        return account;
    }

    @Override
    public List<Account> getlist(String cif) {
        return accountRepository.getListAccount(cif);
    }

    @Override
    public Account credit(String acn, float amount) {
        Account account = em.find(Account.class, acn);
        float balance = account.getBalance();
        account.setBalance(balance + amount);
        return em.merge(account);
    }

    @Override
    public Account debit(String acn, float amount) {
        Account account = em.find(Account.class, acn);
        float balance = account.getBalance();
        account.setBalance(balance - amount);
        return em.merge(account);
    }

}

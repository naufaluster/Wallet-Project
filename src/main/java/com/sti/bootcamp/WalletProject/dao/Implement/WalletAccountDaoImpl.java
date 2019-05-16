package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.dao.WalletAccountDao;
import com.sti.bootcamp.WalletProject.model.Account;
import com.sti.bootcamp.WalletProject.model.Wallet;
import com.sti.bootcamp.WalletProject.model.WalletAccount;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import com.sti.bootcamp.WalletProject.repository.AccountRepository;
import com.sti.bootcamp.WalletProject.repository.WalletAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WalletAccountDaoImpl implements WalletAccountDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private WalletAccountRepository walletAccountRepository;

    @Autowired
    private AccountRepository ar;

    @Override
    public List<WalletAccount> getlist() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<WalletAccount> query = builder.createQuery(WalletAccount.class);
        Root<WalletAccount> root = query.from(WalletAccount.class);

        query.select(root);

        Query q = em.createQuery(query);

        return q.getResultList();
    }

    @Override
    public WalletAccount addWallet(WalletAccount walletAccount) {
        return walletAccountRepository.save(walletAccount);
    }

    @Override
    public List<WalletAccount> getListWallet(String accountnumber) {
        return walletAccountRepository.getwallet(accountnumber);
    }

    @Override
    public WalletAccount delete(int id) {
        WalletAccount walletaccount = em.find(WalletAccount.class, id);
        em.remove(walletaccount);
        return walletaccount;
    }

    @Override
    public List<WalletAccount> getlistbyCif(String cif) {
        return walletAccountRepository.getListAccount(cif);
    }

    @Override
    public WalletAccount topup(WalletAccount walletAccount) {
        Account acc = ar.findById(walletAccount.getAccountnumber()).orElse(null);
        acc.setBalance(acc.getBalance()-walletAccount.getAmount());
        ar.save(acc);
        return walletAccountRepository.save(walletAccount);
    }




}

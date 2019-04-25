package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.dao.WalletDao;
import com.sti.bootcamp.WalletProject.model.Wallet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WalletDaoImpl implements WalletDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Wallet> getList() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Wallet> query = builder.createQuery(Wallet.class);
        Root<Wallet> root = query.from(Wallet.class);

        query.select(root);

        Query q = em.createQuery(query);

        return q.getResultList();
    }
}

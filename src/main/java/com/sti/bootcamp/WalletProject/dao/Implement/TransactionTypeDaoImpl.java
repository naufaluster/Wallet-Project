package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.dao.TransactionTypeDao;
import com.sti.bootcamp.WalletProject.model.TransactionType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransactionTypeDaoImpl implements TransactionTypeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TransactionType> getlist() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TransactionType> query = builder.createQuery(TransactionType.class);
        Root<TransactionType> root = query.from(TransactionType.class);

        query.select(root);

        Query q = em.createQuery(query);

        return q.getResultList();
    }

}
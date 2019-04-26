package com.sti.bootcamp.WalletProject.dao.Implement;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.config.UserException;
import com.sti.bootcamp.WalletProject.dao.CustomerDao;
import com.sti.bootcamp.WalletProject.model.Customer;
import com.sti.bootcamp.WalletProject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getList() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);

        query.select(root);

        Query q = em.createQuery(query);

        return q.getResultList();
    }

    @Override
    public Customer getById(String cif) throws NotFoundException {
        return em.find(Customer.class, cif);
    }

    @Override
    public Customer register(Customer customer) throws NotFoundException {
        System.out.println(customer);
        String cif="";
        Query query = em.createQuery("FROM Customer order by cif desc");
        query.setMaxResults(1);
        if(query.getResultList().isEmpty()){
            cif = "CIF-0001";
        } else {
            Customer cus = (Customer) query.getSingleResult();
            int cifn =cus.getCif().length();
            String tid= cus.getCif().substring(5,cifn);
            cif="CIF-"+String.format("%04d", Integer.parseInt(tid) +1);
        }
        customer.setCif(cif);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer login(Customer customer) throws NotFoundException {
        if (customerRepository.findByUsername(customer.getUsername())!=null) {
            Customer cust = customerRepository.findByUsername(customer.getUsername());
            if (customer.getPassword().equals(cust.getPassword())) {
                return cust;
            }
            throw new NotFoundException("02", "Wrong Username or password");
        }
        throw new NotFoundException("02", "Wrong Username or password");
    }

    @Override
    public Customer update(Customer customer) throws NotFoundException {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getUname(String username) throws UserException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer != null){
            throw new UserException("66","Username exist");
        } else {
            return customer;
        }

    }

}

package com.sti.bootcamp.WalletProject.controller;

import com.sti.bootcamp.WalletProject.config.NotFoundException;
import com.sti.bootcamp.WalletProject.config.UserException;
import com.sti.bootcamp.WalletProject.dao.CustomerDao;
import com.sti.bootcamp.WalletProject.model.Customer;
import com.sti.bootcamp.WalletProject.model.dto.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/customers")
    public List<Customer> getList() {
        List<Customer> getList = customerDao.getList();
        return getList;
    }

    @PostMapping(path = "/login")
    public CommonResponse<Customer> login(@RequestBody Customer customer) throws NotFoundException {
        CommonResponse<Customer> resp = new CommonResponse<>();
        Customer cust = customerDao.login(customer);
        if(cust != null){
            resp.setData(cust);
        } else {
            resp.setResponseCode("99");
            resp.setResponeMassage("login customer failed");
            resp.setData(null);
            return resp;
        }
        return resp;
    }

    @GetMapping("/customer/{cif}")
    public CommonResponse<Customer> getById(@PathVariable(name="cif") String cif) throws NotFoundException {
        Customer cus = customerDao.getById(cif);
        CommonResponse<Customer> commonResponse = new CommonResponse<>();
        if(cus == null){
            commonResponse.setResponseCode("99");
            commonResponse.setResponeMassage("list customer failed");
            commonResponse.setData(null);
        } else {
            commonResponse.setData(cus);
        }
        return commonResponse;
    }

    @Transactional
    @PostMapping("/customer-post")
    public CommonResponse<Customer> registerCustomer(@RequestBody Customer customer) throws NotFoundException, UserException {
        CommonResponse<Customer> comResp = new CommonResponse<>();
        Customer uname = customerDao.getUname(customer.getUsername());
//        Customer cus = customerDao.register(customer);
        comResp.setData(customerDao.register(customer));
//            comResp.setData(cus);

        return comResp;
    }

    @PutMapping("/update-customer")
    public CommonResponse<Customer> update(@RequestBody Customer customer) throws NotFoundException {
        CommonResponse<Customer> comResp = new CommonResponse<Customer>();
        Customer cus = customerDao.update(customer);
        if(cus != null){
            comResp.setData(cus);
        } else {
            comResp.setResponseCode("99");
            comResp.setResponeMassage("update customer failed");
            comResp.setData(null);
        }

        return comResp;
    }


    
}
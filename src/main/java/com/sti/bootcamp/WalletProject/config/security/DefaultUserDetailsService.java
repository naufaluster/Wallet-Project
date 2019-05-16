package com.sti.bootcamp.WalletProject.config.security;

import com.sti.bootcamp.WalletProject.dao.CustomerDao;
import com.sti.bootcamp.WalletProject.exception.NotFoundException;
import com.sti.bootcamp.WalletProject.exception.UserException;
import com.sti.bootcamp.WalletProject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mockUser(username);
    }

    private UserDetails mockUser(String username)  {
        try {
            Customer customer = customerDao.getUname(username);
            if (customer == null) {
                throw new NotFoundException("99", "Failed");
            }
            return new User(customer.getUsername(), String.format("{noop}%s", customer.getPassword()), getAuthority());
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Invalid username or password");
        } catch (UserException e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}

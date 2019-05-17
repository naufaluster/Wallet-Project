package com.sti.bootcamp.WalletProject.config.security;

import com.sti.bootcamp.WalletProject.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUser implements UserDetails {

    private static final long serialVersionUID = -6595290709882074455L;

    private Customer customer;
    private Collection authorities;

    public CustomUser(Customer customer, Collection authorities) {
        this.customer = customer;
        this.authorities = authorities;
    }

    public String getFirstname(){
        if(customer==null){
            return null;
        } else {
            return customer.getFirstname();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        if(customer==null){
            return null;
        } else {
            return String.format("{noop}%s", customer.getPassword());
        }
    }

    @Override
    public String getUsername() {
        if(customer==null){
            return null;
        } else {
            return customer.getUsername();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

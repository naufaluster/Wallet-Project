package com.sti.bootcamp.WalletProject.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "accountnumber")
    private String AccountNumber;

    @Column (name = "accountname")
    private String AccountName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "opendate")
    private Date openDate;

    @Column (name = "balance")
    private float balance;

    @ManyToOne
    @JoinColumn (name = "cif")
    private Customer cif;

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer getCif() {
        return cif;
    }

    public void setCif(Customer cif) {
        this.cif = cif;
    }

}

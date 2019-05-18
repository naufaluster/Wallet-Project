package com.sti.bootcamp.WalletProject.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "date")
    private Date date;

    @Column (name = "accountnumberdebit")
    private String accountNumberDebit;

    @Column (name = "accountnumbercredit")
    private String accountNumberCredit;

    @Column (name = "amount")
    private float amount;

    @Column (name = "transactiontype")
    private String transactionType;

    @ManyToOne
    @JoinColumn (name = "cif")
    private Customer cif;

    public Customer getCif() {
        return cif;
    }

    public void setCif(Customer cif) {
        this.cif = cif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccountNumberDebit() {
        return accountNumberDebit;
    }

    public void setAccountNumberDebit(String accountNumberDebit) {
        this.accountNumberDebit = accountNumberDebit;
    }

    public String getAccountNumberCredit() {
        return accountNumberCredit;
    }

    public void setAccountNumberCredit(String accountNumberCredit) {
        this.accountNumberCredit = accountNumberCredit;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}

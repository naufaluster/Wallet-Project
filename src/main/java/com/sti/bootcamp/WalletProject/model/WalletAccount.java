package com.sti.bootcamp.WalletProject.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "walletaccount")
public class WalletAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;


    @Column (name = "walletid")
    private String walletid;


    @Column (name = "accountnumber")
    private String accountnumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "createdate")
    private Date createdate;

    @Column (name = "notelp")
    private int notelp;

    @Column (name = "amount")
    private float amount;

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

    public String getWalletid() {
        return walletid;
    }

    public void setWalletid(String walletid) {
        this.walletid = walletid;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public int getNotelp() {
        return notelp;
    }

    public void setNotelp(int notelp) {
        this.notelp = notelp;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
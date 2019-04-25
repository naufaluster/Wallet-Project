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

    @ManyToOne
    @JoinColumn (name = "walletid")
    private Wallet walletid;

    @ManyToOne
    @JoinColumn (name = "accountnumber")
    private Account accountnumber;

    @Column (name = "walletname")
    private String walletname;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "createdate")
    private Date createdate;

    @Column (name = "status")
    private String status;

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

    public Wallet getWalletid() {
        return walletid;
    }

    public void setWalletid(Wallet walletid) {
        this.walletid = walletid;
    }

    public Account getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Account accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWalletname() {
        return walletname;
    }

    public void setWalletname(String walletname) {
        this.walletname = walletname;
    }

}
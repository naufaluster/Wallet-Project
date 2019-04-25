package com.sti.bootcamp.WalletProject.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @Column (name = "walletid")
    private String walletid;

    @Column (name = "description")
    private String description;

    public String getWalletid() {
        return walletid;
    }

    public void setWalletid(String walletid) {
        this.walletid = walletid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
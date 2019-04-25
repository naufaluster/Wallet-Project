package com.sti.bootcamp.WalletProject.model;

import javax.persistence.*;

@Entity
@Table (name = "transactiontype")
public class TransactionType {

    @Id
    @Column (name = "code")
    private String code;

    @Column (name = "description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.sti.bootcamp.WalletProject.config;

public class UserException extends BaseCommonException {

    public UserException(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
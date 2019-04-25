package com.sti.bootcamp.WalletProject.config;

public class NotFoundException extends BaseCommonException {

    public NotFoundException(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

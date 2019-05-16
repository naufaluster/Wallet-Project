package com.sti.bootcamp.WalletProject.exception;

public class NotFoundException extends BaseCommonException {

    public NotFoundException(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

package com.sti.bootcamp.WalletProject.model.dto;

public class CommonResponse<T> {

    private String responseCode;
    private String responeMassage;
    private T data;

    public CommonResponse(String responseCode, String responeMassage) {
        super();
        this.responseCode= responseCode;
        this.responeMassage= responeMassage;
    }

    public CommonResponse() {
        this.responseCode="01";
        this.responeMassage="success";
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponeMassage() {
        return responeMassage;
    }

    public void setResponeMassage(String responeMassage) {
        this.responeMassage = responeMassage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

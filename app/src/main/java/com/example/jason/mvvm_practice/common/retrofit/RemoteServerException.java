package com.example.jason.mvvm_practice.common.retrofit;

public class RemoteServerException extends RuntimeException {

    private Integer responseCode;

    private String responseMessage;

    public RemoteServerException(Integer responseCode, String responseMessage) {
        super(String.format("ResponseCode: %s, responseMessage: %s", responseCode, responseMessage));
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }


    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}

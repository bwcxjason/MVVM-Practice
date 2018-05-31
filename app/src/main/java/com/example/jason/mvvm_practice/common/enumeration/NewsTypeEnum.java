package com.example.jason.mvvm_practice.common.enumeration;


public enum NewsTypeEnum {

    APP_NEWS("0"),

    APP_COMPANY_NEWS("5"),

    APP_INFORMATION("6"),

    APP_PUSH("10");

    private String value;

    NewsTypeEnum(String value) {
        this.value = value;
    }

    public String toValue() {
        return this.value;
    }
}


package com.example.jason.mvvm_practice.common.util;

public class CalculationUtil {

    public static int calculateTotalPrice(int price, int number, int discount) {
        return price * number - discount;
    }

}

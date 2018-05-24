package com.example.jason.mvvm_practice.common;

import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;

public class CustomConversion {

    /**
     * 正例(解决databinding设置颜色值报错问题)
     *
     * @param color
     * @return
     */
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }

    /**
     * 反例(databinding赋值String将自动转成Int，会报错)
     *
     * @param value
     * @return
     */
    @BindingConversion
    public static int convertStringToInt(String value) {
        int number;
        try {
            number = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            number = 0;
        }
        return number;
    }

}

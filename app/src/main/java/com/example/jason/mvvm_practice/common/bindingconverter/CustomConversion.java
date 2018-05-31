package com.example.jason.mvvm_practice.common.bindingconverter;

import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class CustomConversion {

    /**
     * 颜色值转Drawable
     *
     * @param color
     * @return
     */
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }

    /**
     * int转String
     * @param value
     * @return
     */
    @BindingConversion
    public static String convertIntToString(int value) {
        return String.valueOf(value);
    }

    @BindingConversion
    public static String convertDrawableToString(Drawable drawable) {
        return new String("test");
    }

}

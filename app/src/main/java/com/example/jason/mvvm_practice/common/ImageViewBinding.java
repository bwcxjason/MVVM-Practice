package com.example.jason.mvvm_practice.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageViewBinding {

    private final static String PLACEHOLDER_GRAPHIC = "http://huangjinbao.oss-cn-hangzhou.aliyuncs.com/hjbsys/images/userHead.jpg";

    @BindingAdapter("bindImage")
    public static void setImage(ImageView imageView, String imageUrl) {
        imageUrl = imageUrl == null ? PLACEHOLDER_GRAPHIC : imageUrl;
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

}

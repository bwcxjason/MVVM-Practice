package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.jason.mvvm_practice.business.articles.model.Article;

import java.util.List;

public class ArticlePageInfo {

    public ObservableList<Article> articles = new ObservableArrayList<>();

    private int mCount = 2;

    public String getmNewsType() {
        return mNewsType;
    }

    public void setmNewsType(String mNewsType) {
        this.mNewsType = mNewsType;
    }

    public void addArticles(List<Article> articleList) {
        articles.addAll(articleList);
        mOffset = articles.size();
    }

    private String mNewsType = "6";
    private int mOffset = 0;
}

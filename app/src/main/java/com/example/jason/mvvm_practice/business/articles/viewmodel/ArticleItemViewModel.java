package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.jason.mvvm_practice.business.articles.model.Article;

public class ArticleItemViewModel extends BaseObservable {

    private ObservableField<Article> mArticleObservable = new ObservableField<>();

    public void setArticle(Article article) {
        this.mArticleObservable.set(article);
    }

    public String getImageUrl() {
        return mArticleObservable.get().getImageUrl();
    }

    public String getTitle() {
        return mArticleObservable.get().getTitle();
    }

}

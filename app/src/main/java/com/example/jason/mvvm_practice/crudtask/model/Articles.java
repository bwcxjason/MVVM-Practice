package com.example.jason.mvvm_practice.crudtask.model;

import java.io.Serializable;
import java.util.List;

public class Articles implements Serializable {

    List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}

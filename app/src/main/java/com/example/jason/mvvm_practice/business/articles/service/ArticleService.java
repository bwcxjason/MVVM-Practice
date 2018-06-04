package com.example.jason.mvvm_practice.business.articles.service;

import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.common.async.ListenableFuture;
import com.example.jason.mvvm_practice.common.retrofit.TargetRetrofitService;

@TargetRetrofitService(RetrofitArticleService.class)
public interface ArticleService {

    ListenableFuture<Articles> getArticles(int count, String newsType, int offset);

}

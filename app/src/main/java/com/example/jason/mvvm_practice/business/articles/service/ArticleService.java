package com.example.jason.mvvm_practice.business.articles.service;

import com.example.jason.mvvm_practice.business.articles.model.Articles;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("/discovery/list")
    Observable<Articles> getArticles(@Query("count") int count, @Query("newsType") String newsType, @Query("offset") int offset);

}

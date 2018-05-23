package com.example.jason.mvvm_practice.crudtask.articles;

import com.example.jason.mvvm_practice.crudtask.model.Articles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("/discovery/list")
    Call<Articles> getArticles(@Query("count") int count, @Query("newsType") String newsType, @Query("offset") int offset);

}

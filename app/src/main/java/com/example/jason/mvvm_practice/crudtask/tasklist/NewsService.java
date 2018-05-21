package com.example.jason.mvvm_practice.crudtask.tasklist;

import com.example.jason.mvvm_practice.crudtask.model.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/news/qihoo")
    NewsList getNewsList(@Query("kw") String kw, @Query("site") String site, @Query("pageToken") String pageToken);

}

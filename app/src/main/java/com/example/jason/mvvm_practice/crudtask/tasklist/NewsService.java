package com.example.jason.mvvm_practice.crudtask.tasklist;

import com.example.jason.mvvm_practice.crudtask.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    @GET("/news/qihoo?apikey=Uf1RCdYhbvH1fLJTmkNFnVSBj0G54pcsKqeKq6mgRXZgMYcjAMuPnGLvm4yBKE5f&kw=nba")
    Call<NewsList> getNewsList();

}

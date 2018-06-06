package com.example.jason.mvvm_practice.business.articles.viewmodel;

import com.example.jason.mvvm_practice.business.articles.model.Articles;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import retrofit2.Call;
import retrofit2.Response;

public class ArticlesViewModelTest {


    @Mock
    private Call<Articles> mCall;

    private Response<Articles> mResponse;

    @Before
    public void init() {

    }

    private void mockData() {

    }

    @Test
    public void testOnResponse() {

    }

}

package com.example.jason.mvvm_practice.business.articles.service;

import com.example.jason.mvvm_practice.common.retrofit.RetrofitProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class  ArticleServiceModule {

    @Provides
    ArticleService provideArticleService() {
        return RetrofitProvider.getInstance().create(ArticleService.class);
    }

}

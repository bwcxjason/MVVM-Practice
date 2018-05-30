package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.databinding.ObservableList;

import com.example.jason.mvvm_practice.business.articles.model.Article;
import com.example.jason.mvvm_practice.business.articles.model.Articles;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Response.class})
public class ArticlesViewModelTest {

    private ArticlesViewModel mArticlesViewModel;
    private ArticlesViewModel.RefreshCallback mRefreshCallback;

    @Mock
    private Call<Articles> mCall;

    private Response<Articles> mResponse;

    @Before
    public void init() {
        mArticlesViewModel = new ArticlesViewModel();
        mRefreshCallback = mArticlesViewModel.new RefreshCallback();

        mockData();
    }

    private void mockData() {
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article();
        article1.setImageUrl("http://aliyun.png");
        article1.setTitle("title1");

        Article article2 = new Article();
        article2.setImageUrl("http://aliyun.png");
        article2.setTitle("title2");

        articleList.add(article1);
        articleList.add(article2);

        mResponse = PowerMockito.mock(Response.class);
        when(mResponse.body().getArticleList()).thenReturn(articleList);
    }

    @Test
    public void testOnResponse() {
        mRefreshCallback.onResponse(mCall, mResponse);
        ObservableList<ArticleItemViewModel> articleItemVMList = mArticlesViewModel.articleVMList;
        ArticleItemViewModel articleItemVM1 = articleItemVMList.get(0);
        assertEquals("http://aliyun.png", articleItemVM1.getImageUrl());
        assertEquals("title1", articleItemVM1.getTitle());

        ArticleItemViewModel articleItemVM2 = articleItemVMList.get(1);
        assertEquals("http://aliyun.png", articleItemVM2.getImageUrl());
        assertEquals("title2", articleItemVM2.getTitle());
    }

}

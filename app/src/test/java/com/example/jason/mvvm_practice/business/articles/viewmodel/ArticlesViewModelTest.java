package com.example.jason.mvvm_practice.business.articles.viewmodel;

import com.example.jason.mvvm_practice.business.articles.model.Article;
import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.business.articles.service.ArticleService;
import com.example.jason.mvvm_practice.testutil.RxImmediateSchedulerRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ArticlesViewModelTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ArticleService mArticleService;

    @Mock
    private ArticlesViewModel.RefreshHandler mRefreshHandler;

    @Mock
    private ArticlesViewModel.Navigator mNavigator;

    @Mock
    private ArticlesViewModel.ArticleEditor mArticleEditor;

    @InjectMocks
    private ArticlesViewModel mArticlesViewModel;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockData();
    }

    private void mockData() {
        List<Article> articleList = new ArrayList<>();

        Article article1 = new Article();
        article1.setTitle("Article1");
        article1.setImageUrl("Article1.png");

        Article article2 = new Article();
        article2.setTitle("Article2");
        article2.setImageUrl("Article2.png");

        articleList.add(article1);
        articleList.add(article2);

        Articles articles = new Articles();
        articles.setArticleList(articleList);
        Observable<Articles> observable = Observable.just(articles);

        when(mArticleService.getArticles(anyInt(), anyString(), anyInt())).thenReturn(observable);
    }

    @Test
    public void testOnRefresh() {
        mArticlesViewModel.onRefresh();
        List<ArticleItemViewModel> articleItemViewModelList = mArticlesViewModel.articleVMList;
        ArticleItemViewModel article1 = articleItemViewModelList.get(0);
        ArticleItemViewModel article2 = articleItemViewModelList.get(1);

        assertEquals("Article1", article1.title.get());
        assertEquals("Article1.png", article1.imageUrl.get());
        assertEquals("Article2", article2.title.get());
        assertEquals("Article2.png", article2.imageUrl.get());
    }
}

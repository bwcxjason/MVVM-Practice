package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Handler;

import com.example.jason.mvvm_practice.business.articles.model.Article;
import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.business.articles.service.ArticleService;
import com.example.jason.mvvm_practice.common.command.ReplyAction;
import com.example.jason.mvvm_practice.common.retrofit.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesViewModel extends ViewModel {

    private static ArticleService sArticleService;

    public ObservableList<ArticleItemViewModel> articleVMList = new ObservableArrayList<>();

    private RefreshHandler mRefreshHandler;
    private Navigator mNavigator;


    private int mCount = 2;
    private String mNewsType = "6";


    static {
        sArticleService = RetrofitProvider.getInstance().create(ArticleService.class);
    }

    public void loadArticles() {
        Call<Articles> call = sArticleService.getArticles(mCount, mNewsType, articleVMList.size());
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                articleVMList.addAll(convertArticleListToArticleVMList(response.body().getArticleList()));
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                System.out.println(Thread.activeCount());
            }
        });
    }

    private Handler mHandler = new Handler();

    public ReplyAction refresh = new ReplyAction() {
        @Override
        public void execute() {
            Call<Articles> call = sArticleService.getArticles(mCount, mNewsType, 0);
            call.enqueue(new RefreshCallback());
        }
    };

    public ReplyAction loadMore = new ReplyAction() {
        @Override
        public void execute() {
            Call<Articles> call = sArticleService.getArticles(mCount, mNewsType, articleVMList.size());
            call.enqueue(new LoadMoreCallback());
        }
    };

    class RefreshCallback implements Callback<Articles> {

        @Override
        public void onResponse(Call<Articles> call, Response<Articles> response) {
            articleVMList.clear();
            articleVMList.addAll(convertArticleListToArticleVMList(response.body().getArticleList()));

            mHandler.post(() -> mRefreshHandler.onRefreshFinish());
        }

        @Override
        public void onFailure(Call<Articles> call, Throwable t) {

        }
    }

    class LoadMoreCallback implements Callback<Articles> {

        @Override
        public void onResponse(Call<Articles> call, Response<Articles> response) {
            articleVMList.addAll(convertArticleListToArticleVMList(response.body().getArticleList()));
            mHandler.post(() -> mRefreshHandler.onLoadMoreFinish());
        }

        @Override
        public void onFailure(Call<Articles> call, Throwable t) {

        }
    }

    public interface RefreshHandler {
        void onRefreshFinish();

        void onLoadMoreFinish();
    }

    public interface Navigator {
        void goToLogin();
    }

    public void onClickLoginButton() {
        mNavigator.goToLogin();
    }

    public void setRefreshHandler(RefreshHandler mRefreshHandler) {
        this.mRefreshHandler = mRefreshHandler;
    }

    public void setNavigator(Navigator navigator) {
        mNavigator = navigator;
    }

    private List<ArticleItemViewModel> convertArticleListToArticleVMList(List<Article> articleList) {
        List<ArticleItemViewModel> articleItemVMlList = new ArrayList<>();
        if (articleList == null) {
            return articleItemVMlList;
        }

        for (int i = 0; i < articleList.size(); i++) {
            ArticleItemViewModel articleItemVM = new ArticleItemViewModel();
            articleItemVM.setArticle(articleList.get(i));
            articleItemVMlList.add(articleItemVM);
        }

        return articleItemVMlList;
    }

}
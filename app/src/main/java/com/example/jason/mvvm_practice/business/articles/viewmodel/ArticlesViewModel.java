package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Handler;

import com.example.jason.mvvm_practice.business.articles.model.Article;
import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.business.articles.service.ArticleService;
import com.example.jason.mvvm_practice.common.async.ListenableFuture;
import com.example.jason.mvvm_practice.common.command.Command;
import com.example.jason.mvvm_practice.common.constant.Constant;
import com.example.jason.mvvm_practice.common.enumeration.NewsTypeEnum;
import com.example.jason.mvvm_practice.common.retrofit.RetrofitToCommonProxyAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesViewModel extends ViewModel {

    private ArticleService articleService = RetrofitToCommonProxyAdapterFactory.getProxyInstance(ArticleService.class);

    public ObservableList<ArticleItemViewModel> articleVMList = new ObservableArrayList<>();

    private RefreshHandler mRefreshHandler;
    private Navigator mNavigator;


    private Handler mHandler = new Handler();

    public Command refresh = new Command() {
        @Override
        public void action() {
            ListenableFuture<Articles> future = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), 0);
            future.addCallback(articles -> handleRefreshSuccess(articles),
                    ex -> handleRefreshFailure(ex));
        }
    };

    private void handleRefreshSuccess(Articles articles) {
        articleVMList.clear();
        articleVMList.addAll(convertArticleListToArticleVMList(articles.getArticleList()));

        mHandler.post(() -> mRefreshHandler.onRefreshFinish());
    }

    private void handleRefreshFailure(Throwable e) {
        // TODO
        System.out.println();
    }

    private void handleLoadMoreSuccess(Articles articles) {
        articleVMList.addAll(convertArticleListToArticleVMList(articles.getArticleList()));
        mHandler.post(() -> mRefreshHandler.onLoadMoreFinish());
    }

    private void handleLoadMoreFailure(Throwable e) {
        // TODO
    }

    public Command loadMore = new Command() {
        @Override
        public void action() {
            ListenableFuture<Articles> future = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), 0);
            future.addCallback(articles -> handleLoadMoreSuccess(articles),
                    ex -> handleLoadMoreFailure(ex));
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

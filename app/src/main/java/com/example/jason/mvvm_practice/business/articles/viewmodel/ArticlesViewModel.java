package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Handler;

import com.example.jason.mvvm_practice.business.articles.model.Article;
import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.business.articles.service.ArticleService;
import com.example.jason.mvvm_practice.common.command.Command;
import com.example.jason.mvvm_practice.common.constant.Constant;
import com.example.jason.mvvm_practice.common.enumeration.NewsTypeEnum;
import com.example.jason.mvvm_practice.common.retrofit.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ArticlesViewModel extends ViewModel {

    private ArticleService articleService = RetrofitProvider.getInstance().create(ArticleService.class);

    public ObservableList<ArticleItemViewModel> articleVMList = new ObservableArrayList<>();

    private RefreshHandler mRefreshHandler;
    private Navigator mNavigator;
    private ArticleEditor mEditor;
    private Handler mHandler = new Handler();

    public Command refresh = () -> {
        Observable<Articles> observable = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), 0);
        observable.subscribe(this::handleRefreshSuccess, this::handleRefreshFailure);
    };

    public Command loadMore = () -> {
        Observable<Articles> observable = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), 0);
        observable.subscribe(this::handleLoadMoreSuccess, this::handleLoadMoreFailure);

    private void handleRefreshSuccess(Articles articles) {
        articleVMList.clear();
        articleVMList.addAll(convertArticleListToArticleVMList(articles.getArticleList()));

        mHandler.post(() -> mRefreshHandler.onRefreshFinish());
    }

    private void handleRefreshFailure(Throwable e) {
        e.printStackTrace();
    }

    private void handleLoadMoreSuccess(Articles articles) {
        articleVMList.addAll(convertArticleListToArticleVMList(articles.getArticleList()));
        mHandler.post(() -> mRefreshHandler.onLoadMoreFinish());
    }

    private void handleLoadMoreFailure(Throwable e) {

        e.printStackTrace();
    }

    public interface RefreshHandler {
        void onRefreshFinish();

        void onLoadMoreFinish();
    }

    public interface Navigator {
        void goToLogin();
    }

    public interface ArticleEditor {
        void editArticle(ArticleItemViewModel itemViewModel);
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

    public void setEditor(ArticleEditor editor) {
        mEditor = editor;
    }

    private List<ArticleItemViewModel> convertArticleListToArticleVMList(List<Article> articleList) {
        List<ArticleItemViewModel> articleItemVMlList = new ArrayList<>();
        if (articleList == null) {
            return articleItemVMlList;
        }

        for (int i = 0; i < articleList.size(); i++) {
            ArticleItemViewModel articleItemVM = new ArticleItemViewModel();
            articleItemVM.setArticle(articleList.get(i));
            articleItemVM.setEditor(mEditor);
            articleItemVMlList.add(articleItemVM);
        }

        return articleItemVMlList;
    }

}

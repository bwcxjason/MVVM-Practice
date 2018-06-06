package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.jason.mvvm_practice.business.articles.model.Article;
import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.business.articles.service.ArticleService;
import com.example.jason.mvvm_practice.common.command.Command;
import com.example.jason.mvvm_practice.common.constant.Constant;
import com.example.jason.mvvm_practice.common.di.component.DaggerArticleServiceComponent;
import com.example.jason.mvvm_practice.common.enumeration.NewsTypeEnum;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ArticlesViewModel extends ViewModel {

    @Inject
    ArticleService articleService;

    public ArticlesViewModel() {
        DaggerArticleServiceComponent.create().inject(this);
    }

    public ObservableList<ArticleItemViewModel> articleVMList = new ObservableArrayList<>();

    private RefreshHandler mRefreshHandler;
    private Navigator mNavigator;
    private ArticleEditor mEditor;

    public Command refresh = () -> {
        Observable<Articles> observable = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), 0);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRefreshSuccess, this::handleRefreshFailure);
    };

    public Command loadMore = () -> {
        Observable<Articles> observable = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), 0);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoadMoreSuccess, this::handleLoadMoreFailure);
    };

    private void handleRefreshSuccess(Articles articles) {
        articleVMList.clear();
        articleVMList.addAll(convertArticleListToArticleVMList(articles.getArticleList()));

        mRefreshHandler.onRefreshFinish();
    }

    private void handleRefreshFailure(Throwable e) {
        e.printStackTrace();
    }

    private void handleLoadMoreSuccess(Articles articles) {
        articleVMList.addAll(convertArticleListToArticleVMList(articles.getArticleList()));
        mRefreshHandler.onLoadMoreFinish();
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

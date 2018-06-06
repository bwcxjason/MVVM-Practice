package com.example.jason.mvvm_practice.business.articles.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.jason.mvvm_practice.business.articles.model.Articles;
import com.example.jason.mvvm_practice.business.articles.service.ArticleService;
import com.example.jason.mvvm_practice.common.constant.Constant;
import com.example.jason.mvvm_practice.common.di.component.DaggerArticleServiceComponent;
import com.example.jason.mvvm_practice.common.enumeration.NewsTypeEnum;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

    public void onRefresh() {
        getArticles(articleVMList.size(), this::handleRefreshSuccess, this::handleRefreshFailure);
    }

    public void onLoadMore() {
        getArticles(articleVMList.size(), this::handleLoadMoreSuccess, this::handleLoadMoreFailure);
    }

    @SuppressLint("CheckResult")
    private void getArticles(int offset, Consumer<List<ArticleItemViewModel>> next, Consumer<Throwable> error) {
        Observable<Articles> observable = articleService.getArticles(Constant.PAGE_ITEMS_COUNT, NewsTypeEnum.APP_INFORMATION.toValue(), offset);
        observable
                .flatMap(articles -> Observable.fromIterable(articles.getArticleList()))
                .map(article -> {
                    ArticleItemViewModel articleItemVM = new ArticleItemViewModel();
                    articleItemVM.setArticle(article);
                    articleItemVM.setEditor(mEditor);
                    return articleItemVM;
                })
                .toList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    private void handleRefreshSuccess(List<ArticleItemViewModel> itemViewModels) {
        articleVMList.clear();
        articleVMList.addAll(itemViewModels);
        mRefreshHandler.onRefreshFinish();
    }

    private void handleRefreshFailure(Throwable e) {
        e.printStackTrace();
    }

    private void handleLoadMoreSuccess(List<ArticleItemViewModel> itemViewModels) {
        articleVMList.addAll(itemViewModels);
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

}

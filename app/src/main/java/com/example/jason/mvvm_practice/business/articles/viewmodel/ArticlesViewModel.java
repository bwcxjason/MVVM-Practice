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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesViewModel extends ViewModel {

    public ObservableList<Article> articles = new ObservableArrayList<>();

    private RefreshHandler mRefreshHandler;
    private Navigator mNavigator;

    private int mCount = 2;
    private String mNewsType = "6";
    private int mOffset = 0;

    public interface RefreshHandler {
        void onRefreshFinish(List<Article> articles);

        void onLoadMoreFinish(List<Article> articles);
    }

    public interface Navigator {
        void goToLogin();
    }

    public void loadArticles() {
        ArticleService service = RetrofitProvider.getInstance().create(ArticleService.class);
        Call<Articles> call = service.getArticles(mCount, mNewsType, mOffset);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                articles.addAll(response.body().getArticleList());
                mOffset += mCount;
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                System.out.println(Thread.activeCount());
            }
        });
    }

    public ReplyAction refresh = new ReplyAction() {
        @Override
        public void execute() {
            loadArticles();
            Handler handler = new Handler();
            handler.post(() -> mRefreshHandler.onRefreshFinish(null));
        }
    };

    public ReplyAction loadMore = new ReplyAction() {
        @Override
        public void execute() {
            loadArticles();
            Handler handler = new Handler();
            handler.post(() -> mRefreshHandler.onLoadMoreFinish(null));
        }
    };

    public void onClickLoginButton() {
        mNavigator.goToLogin();
    }

    public void setRefreshHandler(RefreshHandler mRefreshHandler) {
        this.mRefreshHandler = mRefreshHandler;
    }

    public void setNavigator(Navigator navigator) {
        mNavigator = navigator;
    }

}

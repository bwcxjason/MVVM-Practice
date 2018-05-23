package com.example.jason.mvvm_practice.crudtask.articles;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Handler;

import com.example.jason.mvvm_practice.crudtask.model.Article;
import com.example.jason.mvvm_practice.crudtask.model.Articles;
import com.example.jason.mvvm_practice.retrofit.RetrofitProvider;

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

    public Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            Handler handler = new Handler();
            new Thread(() -> {

                loadArticles();
                handler.post(() -> mRefreshHandler.onRefreshFinish(null));

            }).start();
        }
    };

    public Runnable loadMoreRunnable = new Runnable() {
        @Override
        public void run() {
            Handler handler = new Handler();
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(() -> mRefreshHandler.onLoadMoreFinish(null));
            }).start();
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

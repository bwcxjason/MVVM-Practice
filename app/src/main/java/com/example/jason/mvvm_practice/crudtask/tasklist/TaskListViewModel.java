package com.example.jason.mvvm_practice.crudtask.tasklist;

import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import com.example.jason.mvvm_practice.crudtask.model.NewsList;
import com.example.jason.mvvm_practice.retrofit.RetrofitProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListViewModel extends ViewModel {

    private RefreshHandler mRefreshHandler;
    private Navigator mNavigator;

    public interface RefreshHandler {
        void onRefreshFinish(NewsList list);
        void onLoadMoreFinish(NewsList list);
    }

    public interface Navigator {
        void goToLogin();
    }

    public void loadNewsList() {
        NewsService service = RetrofitProvider.getInstance().create(NewsService.class);
        Call<NewsList> newsListConnecation = service.getNewsList();
        newsListConnecation.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                System.out.println(response.body().getData().toString());
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                System.out.println(Thread.activeCount());
            }
        });
    }

    public Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            Handler handler = new Handler();
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

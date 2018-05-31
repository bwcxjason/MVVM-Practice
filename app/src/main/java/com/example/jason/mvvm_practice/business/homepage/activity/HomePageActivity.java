package com.example.jason.mvvm_practice.business.homepage.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jason.mvvm_practice.BR;
import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.business.articles.viewmodel.ArticleItemViewModel;
import com.example.jason.mvvm_practice.business.articles.viewmodel.ArticlesViewModel;
import com.example.jason.mvvm_practice.common.adapter.ListViewAdapter;
import com.example.jason.mvvm_practice.databinding.ActivityHomepageBinding;

public class HomePageActivity extends AppCompatActivity implements ArticlesViewModel.RefreshHandler, ArticlesViewModel.Navigator {

    private ActivityHomepageBinding mArticlesBinding;
    private ArticlesViewModel mArticlesViewModel;
    private ListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArticlesBinding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);
        mArticlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        mArticlesViewModel.setRefreshHandler(this);
        mArticlesViewModel.setNavigator(this);
        mArticlesBinding.setArticlesViewModel(mArticlesViewModel);

        setupListAdapter();
    }

    private void setupListAdapter() {
        ListView listView = mArticlesBinding.articleLayout.articleListView;
        mListViewAdapter = new ListViewAdapter<ArticleItemViewModel>(this, R.layout.activity_article_item, BR.articleItemViewModel);
        listView.setAdapter(mListViewAdapter);
    }

    /**
     * TODO 可将此处重复代码封一个Fragment来消除重复
     */
    @Override
    public void onRefreshFinish() {
        Toast.makeText(getBaseContext(), "刷新结束", Toast.LENGTH_LONG).show();
        mArticlesBinding.articleLayout.refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMoreFinish() {
        Toast.makeText(getBaseContext(), "加载更多结束", Toast.LENGTH_LONG).show();
        mArticlesBinding.articleLayout.refreshLayout.finishLoadMore();
    }

    @Override
    public void goToLogin() {

    }
}

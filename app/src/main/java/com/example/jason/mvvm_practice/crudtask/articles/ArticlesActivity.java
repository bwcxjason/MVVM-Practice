package com.example.jason.mvvm_practice.crudtask.articles;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jason.mvvm_practice.MainActivity;
import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.crudtask.model.Article;
import com.example.jason.mvvm_practice.databinding.ActivityArticlesBinding;
import com.example.jason.mvvm_practice.databinding.ActivityArticleItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity implements ArticlesViewModel.RefreshHandler, ArticlesViewModel.Navigator {

    private ActivityArticlesBinding mArticlesBinding;
    private ArticlesViewModel mArticlesViewModel;
    private ArticlesAdapter mArticlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArticlesBinding = DataBindingUtil.setContentView(this, R.layout.activity_articles);
        mArticlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        mArticlesViewModel.setRefreshHandler(this);
        mArticlesViewModel.setNavigator(this);
        mArticlesBinding.setArticlesViewModel(mArticlesViewModel);

        setupListAdapter();
    }

    private void setupListAdapter() {
        ListView listView = mArticlesBinding.articles;

        mArticlesAdapter = new ArticlesAdapter(mArticlesViewModel, new ArrayList<>());
        listView.setAdapter(mArticlesAdapter);
    }

    @Override
    public void onRefreshFinish(List<Article> articles) {
        Toast.makeText(getBaseContext(), "刷新结束", Toast.LENGTH_LONG).show();
        mArticlesBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMoreFinish(List<Article> articles) {
        Toast.makeText(getBaseContext(), "加载更多结束", Toast.LENGTH_LONG).show();
        mArticlesBinding.refreshLayout.finishLoadMore();
    }


    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    static class ArticlesAdapter extends BaseAdapter {

        private ArticlesViewModel mArticlesViewModel;
        private List<Article> mArticles;

        public ArticlesAdapter(ArticlesViewModel articlesViewModel, List<Article> articles) {
            mArticlesViewModel = articlesViewModel;
            mArticles = articles;
        }

        public void replaceData(List<Article> articles) {
            setList(articles);
        }

        @Override
        public int getCount() {
            return mArticles != null ? mArticles.size() : 0;
        }

        @Override
        public Article getItem(int position) {
            return mArticles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Article article = getItem(position);
            ActivityArticleItemBinding articleItemBinding;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                articleItemBinding = ActivityArticleItemBinding.inflate(inflater, parent, false);
            } else {
                articleItemBinding = DataBindingUtil.getBinding(convertView);
            }

            ArticleItemViewModel articleItemViewModel = new ArticleItemViewModel();
            articleItemBinding.setArticleItemViewModel(articleItemViewModel);

            articleItemViewModel.setArticle(article);

            return articleItemBinding.getRoot();
        }

        private void setList(List<Article> articles) {
            mArticles = articles;
            notifyDataSetChanged();
        }
    }
}

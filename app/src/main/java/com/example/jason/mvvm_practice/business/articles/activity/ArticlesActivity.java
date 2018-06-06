package com.example.jason.mvvm_practice.business.articles.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jason.mvvm_practice.BR;
import com.example.jason.mvvm_practice.MainActivity;
import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.business.articles.viewmodel.ArticleItemViewModel;
import com.example.jason.mvvm_practice.business.articles.viewmodel.ArticlesViewModel;
import com.example.jason.mvvm_practice.common.adapter.ListViewAdapter;
import com.example.jason.mvvm_practice.databinding.ActivityArticlesBinding;

public class ArticlesActivity extends AppCompatActivity implements ArticlesViewModel.RefreshHandler, ArticlesViewModel.Navigator, ArticlesViewModel.ArticleEditor {

    private ActivityArticlesBinding mArticlesBinding;
    private ArticlesViewModel mArticlesViewModel;
    private ListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArticlesBinding = DataBindingUtil.setContentView(this, R.layout.activity_articles);
        mArticlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        mArticlesViewModel.setRefreshHandler(this);
        mArticlesViewModel.setNavigator(this);
        mArticlesViewModel.setEditor(this);
        mArticlesBinding.setArticlesViewModel(mArticlesViewModel);

        setupListAdapter();

        mArticlesBinding.refreshLayout.autoRefresh();
    }

    private void setupListAdapter() {
        ListView listView = mArticlesBinding.articleListView;
        mListViewAdapter = new ListViewAdapter<ArticleItemViewModel>(this, R.layout.activity_article_item, BR.articleItemViewModel);
        listView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onRefreshFinish() {
        Toast.makeText(getBaseContext(), "刷新结束", Toast.LENGTH_LONG).show();
        mArticlesBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMoreFinish() {
        Toast.makeText(getBaseContext(), "加载更多结束", Toast.LENGTH_LONG).show();
        mArticlesBinding.refreshLayout.finishLoadMore();
    }


    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void editArticle(ArticleItemViewModel itemViewModel) {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        new AlertDialog.Builder(this).setTitle("Title")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> itemViewModel.title.set(input.getText().toString()))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }


}

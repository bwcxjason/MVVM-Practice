package com.example.jason.mvvm_practice.business.articles.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jason.mvvm_practice.BR;
import com.example.jason.mvvm_practice.MainActivity;
import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.business.articles.viewmodel.ArticleItemViewModel;
import com.example.jason.mvvm_practice.business.articles.viewmodel.ArticlesViewModel;
import com.example.jason.mvvm_practice.common.adapter.ListViewAdapter;
import com.example.jason.mvvm_practice.databinding.FragmentArticleListBinding;

public class ArticleFragment extends Fragment implements ArticlesViewModel.RefreshHandler, ArticlesViewModel.Navigator, ArticlesViewModel.ArticleEditor {

    private FragmentArticleListBinding mBinding;
    private ArticlesViewModel mArticlesViewModel;
    private ListViewAdapter mListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArticlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        mArticlesViewModel.setRefreshHandler(this);
        mArticlesViewModel.setNavigator(this);
        mArticlesViewModel.setEditor(this);
        mBinding.setArticlesViewModel(mArticlesViewModel);

        setupListAdapter();
        mBinding.refreshLayout.autoRefresh();
        
    }

    private void setupListAdapter() {
        mListViewAdapter = new ListViewAdapter<ArticleItemViewModel>(getContext(), R.layout.activity_article_item, BR.articleItemViewModel);
        mBinding.articleListView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onRefreshFinish() {
        Toast.makeText(getContext(), "刷新结束", Toast.LENGTH_LONG).show();
        mBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMoreFinish() {
        Toast.makeText(getContext(), "加载更多结束", Toast.LENGTH_LONG).show();
        mBinding.refreshLayout.finishLoadMore();
    }


    @Override
    public void goToLogin() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void editArticle(ArticleItemViewModel itemViewModel) {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        new AlertDialog.Builder(getContext()).setTitle("Title")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> itemViewModel.title.set(input.getText().toString()))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

}

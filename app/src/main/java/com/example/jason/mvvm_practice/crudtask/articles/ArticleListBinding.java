package com.example.jason.mvvm_practice.crudtask.articles;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import com.example.jason.mvvm_practice.crudtask.model.Article;

import java.util.List;

public class ArticleListBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter("bindItems")
    public static void setItems(ListView listView, List<Article> articles) {
        ArticlesActivity.ArticlesAdapter adapter = (ArticlesActivity.ArticlesAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(articles);
        }
    }

}

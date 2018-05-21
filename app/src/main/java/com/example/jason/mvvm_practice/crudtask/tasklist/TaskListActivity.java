package com.example.jason.mvvm_practice.crudtask.tasklist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.jason.mvvm_practice.MainActivity;
import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.crudtask.model.NewsList;
import com.example.jason.mvvm_practice.databinding.ActivityTasklistBinding;

public class TaskListActivity extends AppCompatActivity implements TaskListViewModel.RefreshHandler, TaskListViewModel.Navigator {

    private ActivityTasklistBinding mTasklistBinding;
    private TaskListViewModel mTaskListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTasklistBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasklist);
        mTaskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);
        mTaskListViewModel.setRefreshHandler(this);
        mTaskListViewModel.setNavigator(this);
        mTasklistBinding.setTaskListViewModel(mTaskListViewModel);

    }

    @Override
    public void onRefreshFinish(NewsList list) {
        Toast.makeText(getBaseContext(), "刷新结束", Toast.LENGTH_LONG).show();
        mTasklistBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMoreFinish(NewsList list) {
        Toast.makeText(getBaseContext(), "加载更多结束", Toast.LENGTH_LONG).show();
        mTasklistBinding.refreshLayout.finishLoadMore();
    }


    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

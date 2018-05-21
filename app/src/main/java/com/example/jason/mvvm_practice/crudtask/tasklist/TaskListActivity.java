package com.example.jason.mvvm_practice.crudtask.tasklist;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jason.mvvm_practice.R;
import com.example.jason.mvvm_practice.databinding.ActivityTasklistBinding;

public class TaskListActivity extends AppCompatActivity {

    private ActivityTasklistBinding mTasklistBinding;
    private TaskListViewModel mTaskListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTasklistBinding = DataBindingUtil.setContentView(this, R.layout.activity_tasklist);
        mTaskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);
        mTasklistBinding.setTaskListViewModel(mTaskListViewModel);
    }

}

package com.example.jason.mvvm_practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jason.mvvm_practice.crudtask.tasklist.TaskListActivity;
import com.example.jason.mvvm_practice.twowaydatabinding.TWDBActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTWDBBtn(View v) {
        Intent intent = new Intent(this, TWDBActivity.class);
        startActivity(intent);
    }

    public void onClickTaskBtn(View v) {
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }

}

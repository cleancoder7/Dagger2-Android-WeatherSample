package com.example.daggerpractice00.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.daggerpractice00.R;
import com.example.daggerpractice00.ui.common.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel> {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public MainViewModel getViewModel() {
        return null;
    }
}

package com.example.daggerpractice00.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.daggerpractice00.R;
import com.example.daggerpractice00.databinding.ActivityMainBinding;
import com.example.daggerpractice00.ui.common.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Inject
    ViewModelProvider.Factory factory;
    @Inject
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInit();
    }

    private void layoutInit() {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setViewModel(getViewModel());

        activityMainBinding.getViewModel().onLoadingLiveData.observe(this, aBoolean -> {
            if(aBoolean) {
                showLoading();
                activityMainBinding.layoutHeader.setVisibility(View.GONE);
            }else {
                hideLoading();
                activityMainBinding.layoutHeader.setVisibility(View.VISIBLE);
            }
        });

        activityMainBinding.getViewModel().locationLiveData.observe(this, locations -> {
            if(locations != null && !locations.isEmpty()) {
                getViewModel().setWeatherQuries(locations);
            }
        });

        activityMainBinding.getViewModel().weatherLiveData.observe(this, shownWeatherInfos -> {
            if(shownWeatherInfos != null && !shownWeatherInfos.isEmpty()) {
                mainAdapter.addWeatherInfo(shownWeatherInfos);
            }
            hideLoading();
        });

        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.setAdapter(mainAdapter);

    }

    @Override
    public MainViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }
}

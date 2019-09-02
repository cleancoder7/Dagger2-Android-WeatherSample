package com.example.daggerpractice00.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice00.R;
import com.example.daggerpractice00.databinding.BaseLoadingBinding;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewModel> extends DaggerAppCompatActivity {

    private T viewModel;

    public abstract T getViewModel();

    private BaseLoadingBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    public synchronized void showLoading() {
        if(binding == null) {
            ViewGroup viewGroup = this.findViewById(android.R.id.content);
            binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.base_loading, null, false);
            viewGroup.addView(binding.getRoot());
        }
        binding.loadingContainer.setVisibility(View.VISIBLE);
        binding.loadingProgress.show();
    }

    public synchronized void hideLoading() {
        if(binding == null) {
            ViewGroup viewGroup = this.findViewById(android.R.id.content);
            binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.base_loading, null, false);
            viewGroup.addView(binding.getRoot());
        }
        binding.loadingContainer.setVisibility(View.GONE);
        binding.loadingProgress.hide();
    }

}


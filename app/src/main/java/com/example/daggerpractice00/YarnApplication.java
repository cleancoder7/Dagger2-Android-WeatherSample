package com.example.daggerpractice00;

import com.example.daggerpractice00.di.components.DaggerWeatherMainComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class YarnApplication extends DaggerApplication {

    private static YarnApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static synchronized YarnApplication getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends dagger.android.DaggerApplication> applicationInjector() {
        return DaggerWeatherMainComponent.builder().create(this);
    }

}

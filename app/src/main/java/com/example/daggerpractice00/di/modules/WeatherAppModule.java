package com.example.daggerpractice00.di.modules;

import android.content.Context;

import com.example.daggerpractice00.YarnApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherAppModule {

    @Singleton
    @Provides
    Context provideContext(YarnApplication application){
        return application;
    }
}

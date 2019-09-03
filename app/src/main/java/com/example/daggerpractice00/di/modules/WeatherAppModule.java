package com.example.daggerpractice00.di.modules;

import android.content.Context;

import com.example.daggerpractice00.YarnApplication;
import com.example.daggerpractice00.utils.DateUtil;
import com.google.gson.Gson;

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

    @Singleton
    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    DateUtil getDateUtil() {return new DateUtil();}
}

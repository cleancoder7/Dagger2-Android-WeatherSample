package com.example.daggerpractice00.di.components;

import com.example.daggerpractice00.YarnApplication;
import com.example.daggerpractice00.di.builder.ActivityBuilder;
import com.example.daggerpractice00.di.modules.NetworkModule;
import com.example.daggerpractice00.di.modules.WeatherAppModule;

import javax.inject.Singleton;

import dagger.Component;

import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, NetworkModule.class, WeatherAppModule.class, ActivityBuilder.class})
public interface WeatherMainComponent extends AndroidInjector<YarnApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<YarnApplication> {}
}

package com.example.daggerpractice00.ui;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractice00.api.WeatherService;
import com.example.daggerpractice00.utils.DateUtil;
import com.example.daggerpractice00.utils.ViewModelProviderFactory;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    ViewModelProvider.Factory provideMainActivityViewModel(MainViewModel mainActivityViewModel) {
        return new ViewModelProviderFactory<>(mainActivityViewModel);
    }

    @Provides
    MainRepository provideRepository(WeatherService apiService, Gson gson, DateUtil dateUtil){
        return new MainRepository(apiService, gson, dateUtil);
    }

    @Provides
    MainAdapter provideAdapter() {
        return new MainAdapter();
    }
}

package com.example.daggerpractice00.ui;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractice00.api.WeatherService;
import com.example.daggerpractice00.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    ViewModelProvider.Factory provideMainActivityViewModel(MainViewModel mainActivityViewModel) {
        return new ViewModelProviderFactory<>(mainActivityViewModel);
    }

    @Provides
    MainRepository provideRepository(WeatherService apiService){
        return new MainRepository(apiService);
    }
}

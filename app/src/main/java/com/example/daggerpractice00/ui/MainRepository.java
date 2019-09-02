package com.example.daggerpractice00.ui;

import com.example.daggerpractice00.api.WeatherService;

import javax.inject.Inject;

public class MainRepository {

    private WeatherService apiService;

    @Inject
    public MainRepository(WeatherService apiService) {
        this.apiService = apiService;
    }
}

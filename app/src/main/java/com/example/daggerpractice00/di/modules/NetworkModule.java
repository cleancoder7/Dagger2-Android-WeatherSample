package com.example.daggerpractice00.di.modules;

import com.example.daggerpractice00.api.WeatherService;
import com.example.daggerpractice00.utils.Const;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static int REQUESTED_TIMEOUT = 10;
    private static OkHttpClient okHttpClient;

    @Singleton
    @Provides
    final OkHttpClient providedClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUESTED_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUESTED_TIMEOUT, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(providedClient())
                .build();

        return retrofit;
    }

    @Singleton


    @Provides
    WeatherService provideApiService(Retrofit retrofit){
        return retrofit.create(WeatherService.class);
    }
}

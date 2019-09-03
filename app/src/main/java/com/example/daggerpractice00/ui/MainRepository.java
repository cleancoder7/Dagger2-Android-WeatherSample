package com.example.daggerpractice00.ui;

import android.util.SparseArray;

import com.example.daggerpractice00.api.WeatherService;
import com.example.daggerpractice00.api.response.Location;
import com.example.daggerpractice00.api.response.ShownWeatherInfo;
import com.example.daggerpractice00.api.response.Weather;
import com.example.daggerpractice00.utils.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainRepository {

    private WeatherService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Weather> todays = new ArrayList<>();
    private List<Weather> tomorrows = new ArrayList<>();
    private List<String> cityNames = new ArrayList<>();

    private Gson gson;
    private DateUtil dateUtil;

    @Inject
    MainRepository(WeatherService apiService, Gson gson, DateUtil dateUtil) {
        this.apiService = apiService;
        this.gson = gson;
        this.dateUtil = dateUtil;
    }


    void getLocations(String query, LocationCallback locationCallback) {
        Single<Response<JsonArray>> responseSingle = apiService.fetchLocations(query);
        compositeDisposable.add(responseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<JsonArray>>() {
                    @Override
                    public void onSuccess(Response<JsonArray> jsonArrayResponse) {
                        if(jsonArrayResponse.isSuccessful() && jsonArrayResponse.body() != null) {
                            String jsonArray = jsonArrayResponse.body().toString();
                            Type type = new TypeToken<List<Location>>(){}.getType();
                            List<Location> list = gson.fromJson(jsonArray, type);
                            locationCallback.onCallback(list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    void getWeathers(List<Integer> woeids, SparseArray<String> cityArray, boolean isToday, WeatherCallback weatherCallback) {

        String date = "";

        if(isToday) {
            date = dateUtil.getToday();
        }else {
            date = dateUtil.getTomorrow();
        }

        for(Integer woeid : woeids) {
            Single<Response<JsonArray>> responseSingle = apiService.fetchWeathers(woeid, date);
            compositeDisposable.add(responseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<Response<JsonArray>>(){
                @Override
                public void onSuccess(Response<JsonArray> jsonArrayResponse) {
                    if(jsonArrayResponse.isSuccessful() && jsonArrayResponse.body() != null) {
                        String jsonArray = jsonArrayResponse.body().toString();
                        Type type = new TypeToken<List<Weather>>(){}.getType();
                        List<Weather> list = gson.fromJson(jsonArray, type);

                        if(list != null && !list.isEmpty()) {
                            if(isToday) {
                                todays.add(list.get(0));
                            }else {
                                tomorrows.add(list.get(0));
                                cityNames.add(cityArray.get(woeid));
                            }

                            if(woeids.indexOf(woeid) == woeids.size() - 1) {
                                if(isToday) {
                                    getWeathers(woeids, cityArray, false, weatherCallback);
                                    return;
                                }else {

                                    List<ShownWeatherInfo> weatherInfos = new ArrayList<>();

                                    for(String names : cityNames) {
                                        int index = cityNames.indexOf(names);
                                        ShownWeatherInfo shownWeatherInfo = new ShownWeatherInfo();
                                        shownWeatherInfo.setCityName(names);
                                        shownWeatherInfo.setTodayWeather(todays.get(index));
                                        shownWeatherInfo.setTomorrowWeather(tomorrows.get(index));
                                        weatherInfos.add(shownWeatherInfo);
                                    }

                                    weatherCallback.onCallback(weatherInfos);

                                }
                            }

                        }


                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            }));

        }
    }

    public interface LocationCallback {
        void onCallback(List<Location> locations);
    }

    public interface WeatherCallback {
        void onCallback(List<ShownWeatherInfo> weathers);
    }

}

package com.example.daggerpractice00.api;

import com.google.gson.JsonArray;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("api/location/search/")
    Single<Response<JsonArray>> fetchLocations(@Query("query") String query);

    @GET("api/location/{woeid}/{date}")
    Single<Response<JsonArray>> fetchWeathers(@Path("woeid") int woeid, @Path("date") String date);

}

package com.example.daggerpractice00.ui;

import android.util.SparseArray;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice00.api.response.Location;
import com.example.daggerpractice00.api.response.ShownWeatherInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;
    MutableLiveData<List<Location>> locationLiveData = new MutableLiveData<>();
    MutableLiveData<List<ShownWeatherInfo>> weatherLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> onLoadingLiveData = new MutableLiveData<>();

    public ObservableField<String> observableQuery = new ObservableField<>("");
    public SearchView.OnQueryTextListener onSubmitListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            clear();
            getLocations(query);
            onLoadingLiveData.postValue(true);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Inject
    public MainViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
    }

    private void getLocations(String query) {
        mainRepository.getLocations(query, locations -> locationLiveData.postValue(locations));
    }

    void setWeatherQuries(List<Location> locationList) {

        List<Integer> woeidList = new ArrayList<>();
        SparseArray<String> cityNameArray = new SparseArray<>();

        for(Location location : locationList) {
            woeidList.add(location.getWoeid());
            cityNameArray.put(location.getWoeid(), location.getTitle());
        }

        mainRepository.getWeathers(woeidList, cityNameArray, true, weathers
                -> {
            weatherLiveData.postValue(weathers);
            onLoadingLiveData.postValue(false);
        });

    }

    private void clear() {
        locationLiveData.setValue(new ArrayList<>());
        weatherLiveData.setValue(new ArrayList<>());
        mainRepository.clear();
    }



}

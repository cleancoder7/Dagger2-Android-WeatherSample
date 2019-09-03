package com.example.daggerpractice00.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerpractice00.api.response.ShownWeatherInfo;
import com.example.daggerpractice00.databinding.ListShownWeatherBinding;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.WeatherViewHolder> {

    private List<ShownWeatherInfo> infoList = new ArrayList<>();


    public void addWeatherInfo(List<ShownWeatherInfo> infoList) {

        if(!infoList.isEmpty()) {
            this.infoList.clear();
        }

        this.infoList.addAll(infoList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListShownWeatherBinding binding = ListShownWeatherBinding.inflate(LayoutInflater.from(parent.getContext())
        , parent, false);

        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        if(position < getItemCount()) {
            holder.binding.setWeatherInfo(getItem(position));
        }
    }

    private ShownWeatherInfo getItem(int position) {
        if(infoList != null && !infoList.isEmpty()) {
            return infoList.get(position);
        }
        return null;
    }


    @Override
    public int getItemCount() {

        if(infoList != null && !infoList.isEmpty()) {
            return infoList.size();
        }

        return 0;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        ListShownWeatherBinding binding;

        WeatherViewHolder(@NonNull ListShownWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

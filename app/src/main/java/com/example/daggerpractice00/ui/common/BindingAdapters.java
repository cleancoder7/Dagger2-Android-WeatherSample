package com.example.daggerpractice00.ui.common;

import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerpractice00.utils.Const;

public class BindingAdapters {

    private static void loadGlide(ImageView imageView, String url) {

        Log.d("BindingAdapters", "loadGlide()");

        if(!url.isEmpty()) {
            Glide.with(imageView.getContext()).load(url)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
                    .into(imageView);
        }
    }

    @BindingAdapter("android:WeatherSrc")
    public static void loadWeatherIcon(ImageView imageView, String abbreviation) {

        String url = Const.IMAGE_URL;
        if(!abbreviation.isEmpty()) {
            url += abbreviation+".png";
        }

        loadGlide(imageView, url);
    }

    @BindingAdapter("android:Glidesrc")
    public static void loadGlideImage(ImageView imageView, String url) {
        loadGlide(imageView, url);
    }

    @BindingAdapter("android:query")
    public static void setQuery(SearchView searchView, ObservableField<String> query) {
        if(!searchView.getQuery().toString().isEmpty()) {
            query.set(searchView.getQuery().toString());
        }
    }

    @BindingAdapter("android:onSubmit")
    public static void setOnQuerySubmit(SearchView searchView, SearchView.OnQueryTextListener onQueryTextListener) {
        if(onQueryTextListener != null && searchView != null) {
            searchView.setIconified(false);
            searchView.setOnQueryTextListener(onQueryTextListener);
        }
    }
}

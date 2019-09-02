package com.example.daggerpractice00.di.builder;

import com.example.daggerpractice00.ui.MainActivity;
import com.example.daggerpractice00.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivity();
}
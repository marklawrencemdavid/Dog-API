package com.group2.minidog.network;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.group2.minidog.network.daggermodules.AppModule;
import com.group2.minidog.network.daggermodules.NetworkModule;

public class App extends Application {

    private static AppComponent appComponent;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private static final String RETROFIT_BASE_URL = "https://api.thedogapi.com/v1/";

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, context))
                .networkModule(new NetworkModule(RETROFIT_BASE_URL))
                .build();
    }

    public static AppComponent getAppComponent() { return appComponent; }

    public static Context getContext() { return context; }
}

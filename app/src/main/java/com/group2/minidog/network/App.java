package com.group2.minidog.network;

import android.app.Application;
import android.content.Context;

import com.group2.minidog.network.modules.AppModule;

public class App extends Application {

    private static AppComponent appComponent;
    private static Context context;

    private static final String RETROFIT_BASE_URL = "https://api.thedogapi.com/v1/breeds?limit=10&page=0";

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, context))
                .build();
    }

    public static AppComponent getAppComponent() { return appComponent; }

    public static Context getContext() { return context; }
}

package com.group2.minidog.network.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;
    private final Context context;

    public AppModule(Application application, Context context){
        this.application = application;
        this.context = context;
    }

    @Provides
    @Singleton
    public Application providesApplication(){ return application; }

    @Provides
    @Singleton
    public Context getContext() { return context; }
}

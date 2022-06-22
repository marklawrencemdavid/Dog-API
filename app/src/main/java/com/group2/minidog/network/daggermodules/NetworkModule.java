package com.group2.minidog.network.daggermodules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group2.minidog.network.sqlite.DogDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String RETROFIT_BASE_URL;

    public NetworkModule(String RETROFIT_BASE_URL) {
        this.RETROFIT_BASE_URL = RETROFIT_BASE_URL;
    }

    @Provides
    @Singleton
    public GsonConverterFactory providesGson(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(RETROFIT_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    public DogDatabase providesDogDatabase(Context context){
        return new DogDatabase(context);
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public FirebaseAuth providesFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public FirebaseUser providesFirebaseUser(FirebaseAuth firebaseAuth){
        return firebaseAuth.getCurrentUser();
    }
}

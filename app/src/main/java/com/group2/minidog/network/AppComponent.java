package com.group2.minidog.network;

import com.group2.minidog.network.api.DogAPIManager;
import com.group2.minidog.network.daggermodules.AppModule;
import com.group2.minidog.network.daggermodules.NetworkModule;
import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.sqlite.DogDatabase;
import com.group2.minidog.ui.main.home.HomePresenter;
import com.group2.minidog.ui.main.profile.ProfilePresenter;
import com.group2.minidog.ui.main.saves.SavesPresenter;
import com.group2.minidog.ui.splashscreen.SplashScreenPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    // Network
    void inject(DogAPIManager dogAPIManager);
    void inject(DogDatabase dogDatabase);
    void inject(FirebaseAuthManager firebaseAuthManager);

    // Presenter
    void inject(SplashScreenPresenter splashScreenPresenter);
    void inject(HomePresenter homePresenter);
    void inject(SavesPresenter savesPresenter);
    void inject(ProfilePresenter profilePresenter);
}

package com.rensuuuuuuuu.dogapi.network;

import com.rensuuuuuuuu.dogapi.network.api.DogAPIManager;
import com.rensuuuuuuuu.dogapi.network.daggermodules.AppModule;
import com.rensuuuuuuuu.dogapi.network.daggermodules.NetworkModule;
import com.rensuuuuuuuu.dogapi.network.firebase.FirebaseAuthManager;
import com.rensuuuuuuuu.dogapi.network.sqlite.DogDatabase;
import com.rensuuuuuuuu.dogapi.ui.main.home.HomePresenter;
import com.rensuuuuuuuu.dogapi.ui.main.profile.ProfilePresenter;
import com.rensuuuuuuuu.dogapi.ui.main.saves.SavesPresenter;
import com.rensuuuuuuuu.dogapi.ui.splashscreen.SplashScreenPresenter;

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

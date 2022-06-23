package com.rensuuuuuuuu.dogapi.ui.splashscreen;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;
import com.rensuuuuuuuu.dogapi.network.App;

import javax.inject.Inject;

public class SplashScreenPresenter implements SplashScreenPresenterI {

    private final SplashScreenActivityI splashScreenActivityI;
    @Inject
    public FirebaseUser firebaseUser;

    public SplashScreenPresenter(SplashScreenActivityI splashScreenActivityI, Activity activity){
        App.getAppComponent().inject(this);
        this.splashScreenActivityI = splashScreenActivityI;
    }

    @Override
    public void checkCurrentUser(){
        if(firebaseUser == null){
            splashScreenActivityI.goToSignInactivity();
        }else{
            splashScreenActivityI.goToMainActivity();
        }
    }
}

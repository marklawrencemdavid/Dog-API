package com.rensuuuuuuuu.dogapi.ui.splashscreen;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenPresenter implements SplashScreenPresenterI {

    private final SplashScreenActivityI splashScreenActivityI;
    public FirebaseUser firebaseUser;

    public SplashScreenPresenter(SplashScreenActivityI splashScreenActivityI, Activity activity){
        this.splashScreenActivityI = splashScreenActivityI;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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

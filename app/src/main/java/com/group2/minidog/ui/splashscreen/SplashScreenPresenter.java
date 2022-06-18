package com.group2.minidog.ui.splashscreen;

import android.app.Activity;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerI;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

public class SplashScreenPresenter implements SplashScreenPresenterI, FirebaseAuthManagerListener {

    private SplashScreenActivityI splashScreenActivityI;
    private FirebaseAuthManagerI firebaseAuthManagerI;

    public SplashScreenPresenter(SplashScreenActivityI splashScreenActivityI, Activity activity){
        this.splashScreenActivityI = splashScreenActivityI;
        this.firebaseAuthManagerI = new FirebaseAuthManager(activity, this);
    }

    @Override
    public void checkCurrentUser(){
        if(firebaseAuthManagerI.checkCurrentUser() == null){
            splashScreenActivityI.goToSignInactivity();
        }else{
            splashScreenActivityI.goToMainActivity();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String message) {

    }
}

package com.group2.minidog.ui.splashscreen;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerI;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

public class SplashScreenPresenter implements SplashScreenPresenterI {

    private SplashScreenActivityI splashScreenActivityI;
    private FirebaseAuthManagerI firebaseAuthManagerI;

    public SplashScreenPresenter(SplashScreenActivityI splashScreenActivityI){
        this.splashScreenActivityI = splashScreenActivityI;
        this.firebaseAuthManagerI = new FirebaseAuthManager();
    }

    @Override
    public void checkCurrentUser(){
        if(firebaseAuthManagerI.checkCurrentUser() == null){
            splashScreenActivityI.goToSignInactivity();
        }else{
            splashScreenActivityI.goToMainActivity();
        }
    }
}

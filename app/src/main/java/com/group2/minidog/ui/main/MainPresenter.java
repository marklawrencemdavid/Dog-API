package com.group2.minidog.ui.main;

import android.app.Activity;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerI;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

public class MainPresenter implements MainPresenterI, FirebaseAuthManagerListener {

    private final MainActivityI mainActivityI;
    private final FirebaseAuthManagerI firebaseAuthManagerI;

    public MainPresenter(MainActivityI mainActivityI, Activity activity){
        this.mainActivityI = mainActivityI;
        this.firebaseAuthManagerI = new FirebaseAuthManager(activity, this);
    }

    @Override
    public void signout() {
        firebaseAuthManagerI.signOut();
        mainActivityI.goToSignInActivity();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String message) {

    }
}

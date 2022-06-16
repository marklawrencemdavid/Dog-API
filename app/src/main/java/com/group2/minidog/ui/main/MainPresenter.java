package com.group2.minidog.ui.main;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerI;

public class MainPresenter implements MainPresenterI {

    private final MainActivityI mainActivityI;
    private final FirebaseAuthManagerI firebaseAuthManagerI;

    public MainPresenter(MainActivityI mainActivityI){
        this.mainActivityI = mainActivityI;
        this.firebaseAuthManagerI = new FirebaseAuthManager();
    }
}

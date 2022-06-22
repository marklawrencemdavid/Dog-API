package com.group2.minidog.ui.main;

import android.app.Activity;

public class MainPresenter implements MainPresenterI {

    private final MainActivityI mainActivityI;

    public MainPresenter(MainActivityI mainActivityI, Activity activity){
        this.mainActivityI = mainActivityI;
        onPresenterCreated();
    }

    private void onPresenterCreated() {
        this.mainActivityI.initView();
    }
}

package com.group2.minidog.ui.main.home;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.group2.minidog.model.DogAPIModel;
import com.group2.minidog.network.api.DogAPIManager;
import com.group2.minidog.network.api.DogAPIManagerI;
import com.group2.minidog.network.api.DogAPIManagerListeners;

import java.util.ArrayList;

public class HomePresenter implements HomePresenterI, DogAPIManagerListeners {

    private final HomeFragmentI homeFragmentI;
    private final DogAPIManagerI dogAPIManagerI;
    private String previousNameSearched;

    public HomePresenter(HomeFragmentI homeFragmentI) {
        this.homeFragmentI = homeFragmentI;
        this.dogAPIManagerI = new DogAPIManager(this);
        this.previousNameSearched = "";
    }

    @Override
    public void requestData() {
        homeFragmentI.showProgressBar();
        dogAPIManagerI.getDogsFromAPage();
    }

    @Override
    public void search(String name) {
        String mName = name.trim();
        if(!TextUtils.isEmpty(mName)) {
            if(!mName.equals(previousNameSearched)){
                previousNameSearched = mName;
                homeFragmentI.showProgressBar();
                dogAPIManagerI.searchDog(mName);
            }
        }
    }

    @Override
    public void onSuccess(ArrayList<DogAPIModel> dogAPIModels) {
        homeFragmentI.hideProgressBar();
        homeFragmentI.setDataToRecyclerview(dogAPIModels);
    }

    @Override
    public void onFail(@NonNull Throwable t) {
        homeFragmentI.hideProgressBar();
        homeFragmentI.showToast(t.getMessage());
    }
}

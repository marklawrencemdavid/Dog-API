package com.rensuuuuuuuu.dogapi.ui.main.home;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.rensuuuuuuuu.dogapi.model.DogAPIModel;
import com.rensuuuuuuuu.dogapi.network.App;
import com.rensuuuuuuuu.dogapi.network.api.DogAPIManager;
import com.rensuuuuuuuu.dogapi.network.api.DogAPIManagerListeners;
import com.rensuuuuuuuu.dogapi.network.sqlite.DogDatabase;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomePresenter implements HomePresenterI, DogAPIManagerListeners {

    private final HomeFragmentI homeFragmentI;
    private final DogAPIManager dogAPIManager;
    private String previousNameSearched = "";
    @Inject
    public DogDatabase dogDatabase;

    public HomePresenter(HomeFragmentI homeFragmentI) {
        App.getAppComponent().inject(this);
        this.homeFragmentI = homeFragmentI;
        this.dogAPIManager = new DogAPIManager(this);
        onPresenterCreated();
    }

    private void onPresenterCreated() {
        homeFragmentI.initView();
        homeFragmentI.showProgressBar();
        dogAPIManager.getDogsFromAPage();
    }

    @Override
    public void search(String name) {
        String mName = name.trim();
        if(!TextUtils.isEmpty(mName)) {
            if(!mName.equals(previousNameSearched)){
                previousNameSearched = mName;
                homeFragmentI.showProgressBar();
                dogAPIManager.searchDog(mName);
            }
        }
    }

    @Override
    public void addDog(DogAPIModel dogAPIModel) {
        if(dogDatabase.addDog(dogAPIModel)){
            homeFragmentI.showToast("Dog saved successfully.");
        }else{
            homeFragmentI.showToast("Failed to save dog.");
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

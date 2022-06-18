package com.group2.minidog.ui.main.home;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public class HomePresenter implements HomePresenterI{

    private final HomeFragmentI homeFragmentI;
    private final HomeModel homeModel;

    public HomePresenter(HomeFragmentI homeFragmentI) {
        this.homeFragmentI = homeFragmentI;
        this.homeModel = new HomeModel(this);
    }


    @Override
    public void requestData() {
        homeFragmentI.showProgressBar();
        homeModel.getDogs(this);
    }

    @Override
    public void onSuccess(ArrayList<DogModel> dogModels) {
        homeFragmentI.hideProgressBar();
        homeFragmentI.setDataToRecyclerview(dogModels);
    }

    @Override
    public void onFail(Throwable t) {
        homeFragmentI.hideProgressBar();
        homeFragmentI.showToast(t.getMessage());
    }
}

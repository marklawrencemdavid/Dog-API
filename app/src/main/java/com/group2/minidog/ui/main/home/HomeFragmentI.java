package com.group2.minidog.ui.main.home;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public interface HomeFragmentI {
    void initView();

    void setDataToRecyclerview(ArrayList<DogModel> dogModels);
    void showToast(String message);

    void showProgressBar();
    void hideProgressBar();
}

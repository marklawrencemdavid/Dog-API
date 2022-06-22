package com.group2.minidog.ui.main.home;

import com.group2.minidog.model.DogAPIModel;

import java.util.ArrayList;

public interface HomeFragmentI {
    void initView();

    void setDataToRecyclerview(ArrayList<DogAPIModel> dogAPIModels);
    void showToast(String message);

    void showProgressBar();
    void hideProgressBar();
}

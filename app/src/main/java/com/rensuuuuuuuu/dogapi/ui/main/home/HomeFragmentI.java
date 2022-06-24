package com.rensuuuuuuuu.dogapi.ui.main.home;

import com.rensuuuuuuuu.dogapi.model.DogAPIModel;

import java.util.ArrayList;

public interface HomeFragmentI {
    void initView();

    void setDataToRecyclerview(ArrayList<DogAPIModel> dogAPIModels);
    void showToast(String message);

    void search(String name);

    void showProgressBar();
    void hideProgressBar();
}

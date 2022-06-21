package com.group2.minidog.ui.main.saves;

import com.group2.minidog.model.DogSQLiteModel;

import java.util.ArrayList;

public interface SavesFragmentI {
    void initView();

    void setDataToRecyclerview(ArrayList<DogSQLiteModel> dogSQLiteModels);
    void notifyAdapter(int position);

    void showToast(String message);

    void showProgressBar();
    void hideProgressBar();
}

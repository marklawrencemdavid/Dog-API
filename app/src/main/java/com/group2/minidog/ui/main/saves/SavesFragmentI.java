package com.group2.minidog.ui.main.saves;

import com.group2.minidog.model.DogSQLiteModel;

import java.util.ArrayList;

public interface SavesFragmentI {
    void initView();

    void setDataToRecyclerview(ArrayList<DogSQLiteModel> dogSQLiteModels);
    void search(String name);

    void notifyAdapter();
    void notifyAdapterUpdate(DogSQLiteModel dogSQLiteModel, int position);
    void notifyAdapterRemove(int position);

    void showSavesEditBSDF(DogSQLiteModel dogSQLiteModel, int posotion);

    void showToast(String message);

    void showProgressBar();
    void hideProgressBar();
}

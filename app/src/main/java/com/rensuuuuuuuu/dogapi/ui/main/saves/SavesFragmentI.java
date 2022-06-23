package com.rensuuuuuuuu.dogapi.ui.main.saves;

import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

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

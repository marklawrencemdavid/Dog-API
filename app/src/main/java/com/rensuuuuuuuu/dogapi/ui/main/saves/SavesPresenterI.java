package com.rensuuuuuuuu.dogapi.ui.main.saves;

import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

public interface SavesPresenterI {
    void onSearch(String name);
    void showSavesEditBSDF(DogSQLiteModel dogSQLiteModel, int position);
    void updateDog(DogSQLiteModel dogSQLiteModel, int position);
    void deleteDog(DogSQLiteModel dogSQLiteModels, int position);
}

package com.group2.minidog.ui.main.saves;

import com.group2.minidog.model.DogSQLiteModel;

public interface SavesPresenterI {
    void onSearch(String name);
    void showSavesEditBSDF(DogSQLiteModel dogSQLiteModel, int position);
    void updateDog(DogSQLiteModel dogSQLiteModel, int position);
    void deleteDog(DogSQLiteModel dogSQLiteModels, int position);
}

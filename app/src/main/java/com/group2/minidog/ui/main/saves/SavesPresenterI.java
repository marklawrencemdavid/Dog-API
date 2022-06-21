package com.group2.minidog.ui.main.saves;

import com.group2.minidog.model.DogSQLiteModel;

public interface SavesPresenterI {
    void getData();

    void deleteData(DogSQLiteModel dogSQLiteModels, int position);
}

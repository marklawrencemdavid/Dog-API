package com.group2.minidog.ui.main.home;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public interface HomePresenterI {
    void requestData();

    void onSuccess(ArrayList<DogModel> dogModels);
    void onFail(Throwable t);
}

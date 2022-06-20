package com.group2.minidog.network.api;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public interface DogAPIManagerListeners {
    void onSuccess(ArrayList<DogModel> dogModels);
    void onFail(Throwable t);
}

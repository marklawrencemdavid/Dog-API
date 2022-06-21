package com.group2.minidog.network.api;

import com.group2.minidog.model.DogAPIModel;

import java.util.ArrayList;

public interface DogAPIManagerListeners {
    void onSuccess(ArrayList<DogAPIModel> dogAPIModels);
    void onFail(Throwable t);
}

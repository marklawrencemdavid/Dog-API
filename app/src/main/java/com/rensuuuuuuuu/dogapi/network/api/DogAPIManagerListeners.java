package com.rensuuuuuuuu.dogapi.network.api;

import com.rensuuuuuuuu.dogapi.model.DogAPIModel;

import java.util.ArrayList;

public interface DogAPIManagerListeners {
    void onSuccess(ArrayList<DogAPIModel> dogAPIModels);
    void onFail(Throwable t);
}

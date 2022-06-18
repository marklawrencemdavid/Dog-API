package com.group2.minidog.ui.main.home;

import androidx.annotation.NonNull;

import com.group2.minidog.model.DogModel;
import com.group2.minidog.network.App;
import com.group2.minidog.network.api.DogAPI;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeModel implements HomeModelI {

    @Inject
    public Retrofit retrofit;
    private final DogAPI dogAPI;
    private final HomePresenterI homePresenterI;

    public HomeModel(HomePresenterI homePresenterI) {
        App.getAppComponent().inject(this);
        dogAPI = retrofit.create(DogAPI.class);
        this.homePresenterI = homePresenterI;
    }

    @Override
    public void getDogs(HomePresenter homePresenter) {
        Call<ArrayList<DogModel>> call = dogAPI.getDogs("10", "0");
        call.enqueue(new Callback<ArrayList<DogModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<DogModel>> call, @NonNull Response<ArrayList<DogModel>> response) {
                ArrayList<DogModel> dogModels = new ArrayList<>();
                if (response.body() != null) {
                    dogModels = response.body();
                }
                homePresenter.onSuccess(dogModels);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<DogModel>> call, @NonNull Throwable t) {
                homePresenter.onFail(t);
            }
        });
    }
}

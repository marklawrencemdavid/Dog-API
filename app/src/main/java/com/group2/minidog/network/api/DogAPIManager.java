package com.group2.minidog.network.api;

import androidx.annotation.NonNull;

import com.group2.minidog.model.DogModel;
import com.group2.minidog.network.App;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DogAPIManager implements DogAPIManagerI {

    @Inject
    public Retrofit retrofit;
    private final DogAPICalls dogAPICalls;
    private final DogAPIManagerListeners listeners;

    public DogAPIManager(DogAPIManagerListeners listeners) {
        App.getAppComponent().inject(this);
        this.dogAPICalls = retrofit.create(DogAPICalls.class);
        this.listeners = listeners;
    }

    @Override
    public void getAllDogs(){
        Call<ArrayList<DogModel>> call = dogAPICalls.getAllDogs();
        call.enqueue(new Callback<ArrayList<DogModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<DogModel>> call, @NonNull Response<ArrayList<DogModel>> response) {
                ArrayList<DogModel> dogModels = new ArrayList<>();
                if (response.body() != null) {
                    dogModels = response.body();
                }
                listeners.onSuccess(dogModels);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<DogModel>> call, @NonNull Throwable t) {
                listeners.onFail(t);
            }
        });
    }

    @Override
    public void getDogsFromAPage(/*String limit, String page*/){
        String limit = "10", page = "1";
        Call<ArrayList<DogModel>> call = dogAPICalls.getDogsFromAPage(limit, page);
        call.enqueue(new Callback<ArrayList<DogModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<DogModel>> call, @NonNull Response<ArrayList<DogModel>> response) {
                ArrayList<DogModel> dogModels = new ArrayList<>();
                if (response.body() != null) {
                    dogModels = response.body();
                }
                listeners.onSuccess(dogModels);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<DogModel>> call, @NonNull Throwable t) {
                listeners.onFail(t);
            }
        });
    }

    @Override
    public void searchDog(String name){
        Call<ArrayList<DogModel>> call = dogAPICalls.searchDog(name);
        call.enqueue(new Callback<ArrayList<DogModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<DogModel>> call, @NonNull Response<ArrayList<DogModel>> response) {
                ArrayList<DogModel> dogModels = new ArrayList<>();
                if (response.body() != null) {
                    dogModels = response.body();
                }
                listeners.onSuccess(dogModels);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<DogModel>> call, @NonNull Throwable t) {
                listeners.onFail(t);
            }
        });
    }
}

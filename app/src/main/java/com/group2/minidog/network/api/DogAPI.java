package com.group2.minidog.network.api;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DogAPI {
    @GET("breeds")
    Call<ArrayList<DogModel>> getDogs();

    @GET("breeds/search")
    Call<ArrayList<DogModel>> searchDog(@Query("q") String name);
}

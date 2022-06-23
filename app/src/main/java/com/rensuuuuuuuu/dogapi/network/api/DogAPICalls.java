package com.rensuuuuuuuu.dogapi.network.api;

import com.rensuuuuuuuu.dogapi.model.DogAPIModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DogAPICalls {

    @GET("breeds")
    Call<ArrayList<DogAPIModel>> getAllDogs();

    @GET("breeds")
    Call<ArrayList<DogAPIModel>> getDogsFromAPage(@Query("limit") String limit, @Query("page") String page);

    @GET("breeds/search")
    Call<ArrayList<DogAPIModel>> searchDog(@Query("q") String name);
}

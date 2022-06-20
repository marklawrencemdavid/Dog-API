package com.group2.minidog.network.sqlite;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public interface DogDatabaseI {
    ArrayList<DogModel> getAllDog();
    boolean addDog(DogModel dogModel);
}

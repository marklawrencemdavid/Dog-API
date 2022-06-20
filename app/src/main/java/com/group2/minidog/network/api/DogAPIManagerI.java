package com.group2.minidog.network.api;

public interface DogAPIManagerI {
    void getAllDogs();
    void getDogsFromAPage(/*String limit, String page*/);
    void searchDog(String name);
}

package com.group2.minidog.ui.main.home;

import com.group2.minidog.model.DogAPIModel;

public interface HomePresenterI {
    void search(String name);

    void addDog(DogAPIModel dogAPIModel);
}

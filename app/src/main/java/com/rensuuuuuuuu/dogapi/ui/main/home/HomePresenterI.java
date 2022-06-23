package com.rensuuuuuuuu.dogapi.ui.main.home;

import com.rensuuuuuuuu.dogapi.model.DogAPIModel;

public interface HomePresenterI {
    void search(String name);

    void addDog(DogAPIModel dogAPIModel);
}

package com.rensuuuuuuuu.dogapi.ui.main.profile;

import com.rensuuuuuuuu.dogapi.model.DogSQLiteModel;

import java.util.ArrayList;

public interface ProfileFragmentI {
    void initView();
    void setDataToRecyclerview(ArrayList<DogSQLiteModel> dogSQLiteModels);
    void setValues(String profilePictureURL, String displayName, String email);
    void goToSignInActivity();
}

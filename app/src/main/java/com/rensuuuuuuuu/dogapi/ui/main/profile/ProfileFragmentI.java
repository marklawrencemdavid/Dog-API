package com.rensuuuuuuuu.dogapi.ui.main.profile;

public interface ProfileFragmentI {
    void initView();
    void setValues(String profilePictureURL, String displayName, String email);
    void goToSignInActivity();
}

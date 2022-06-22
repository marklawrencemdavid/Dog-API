package com.group2.minidog.ui.main.profile;

public interface ProfileFragmentI {
    void initView();
    void setValues(String profilePictureURL, String displayName, String email);
    void goToSignInActivity();
}

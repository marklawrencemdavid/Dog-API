package com.rensuuuuuuuu.dogapi.ui.signin;

public interface SignInActivityI {
    void initView();

    void etEmailRequestFocus();
    void etPasswordRequestFocus();

    void etEmailSetError(String error);
    void etPasswordSetError(String error);

    void showToast(String message);

    void goToMainActivity();
    void gotoSignUpActivity();
}

package com.group2.minidog.ui.signup;

public interface SignUpActivityI {
    void initView();

    void etEmailSetError(String error);
    void etPasswordSetError(String error);
    void etRePasswordSetError(String error);

    void etEmailRequestFocus();
    void etPasswordRequestFocus();
    void etRePasswordRequestFocus();

    void showToast(String message);

    void goToSignInActivity();
}

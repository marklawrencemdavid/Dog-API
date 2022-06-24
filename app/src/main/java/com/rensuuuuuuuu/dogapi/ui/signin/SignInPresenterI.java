package com.rensuuuuuuuu.dogapi.ui.signin;

import android.content.Intent;

public interface SignInPresenterI {
    void showSignInUI();
    void signInWithGoogle(int requestCode, Intent data);
    void signInEmailAndPassword(String email, String password);
}

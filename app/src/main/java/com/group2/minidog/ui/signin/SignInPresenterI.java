package com.group2.minidog.ui.signin;

import android.content.Intent;

public interface SignInPresenterI {
    void showSignInUI();
    void signInWithGoogle(int requestCode, Intent data);
    void signInEmailAndPassword(String email, String password);
}

package com.group2.minidog.ui.signin;

import android.content.Intent;

public interface SignInPresenterI {
    void signInGoogle();
    void showGoogleAccounts(int requestCode, Intent data);
    void signInEmail(String email, String password);
}

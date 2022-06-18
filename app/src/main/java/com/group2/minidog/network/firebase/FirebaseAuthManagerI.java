package com.group2.minidog.network.firebase;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseAuthManagerI {

    FirebaseUser checkCurrentUser();

    void showSignInUI();
    void signInWithGoogle(int requestCode, @Nullable Intent data);

    void signInEmailAndPassword(String email, String password);

    void signUpWithEmailAndPassword(String email, String password);

    void signOut();
}

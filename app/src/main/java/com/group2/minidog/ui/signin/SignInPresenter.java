package com.group2.minidog.ui.signin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

public class SignInPresenter implements SignInPresenterI, FirebaseAuthManagerListener {

    private final SignInActivityI signInActivityI;
    private final FirebaseAuthManager firebaseAuthManager;

    public SignInPresenter(SignInActivityI signInActivityI, Activity activity){
        this.signInActivityI = signInActivityI;
        this.firebaseAuthManager = new FirebaseAuthManager(activity,this);
        onPresenterCreated();
    }

    private void onPresenterCreated() {
        signInActivityI.initView();
    }

    @Override
    public void showSignInUI() {
        firebaseAuthManager.showSignInUI();
    }

    @Override
    public void signInWithGoogle(int requestCode, Intent data){
        firebaseAuthManager.signInWithGoogle(requestCode, data);
    }

    @Override
    public void signInEmailAndPassword(String email, String password) {
        String mEmail = email.trim(), mPassword = password.trim();
        if (TextUtils.isEmpty(mEmail)){
            signInActivityI.etEmailSetError("Email cannot be empty");
            signInActivityI.etEmailRequestFocus();
        }else if (TextUtils.isEmpty(mPassword)){
            signInActivityI.etPasswordSetError("Password cannot be empty");
            signInActivityI.etPasswordRequestFocus();
        }else{
            firebaseAuthManager.signInEmailAndPassword(mEmail, mPassword);
        }
    }

    @Override
    public void onSuccess() {
        signInActivityI.goToMainActivity();
    }

    @Override
    public void onFail(String message) {
        signInActivityI.showToast(message);
    }
}

package com.group2.minidog.ui.signin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerI;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

public class SignInPresenter implements SignInPresenterI, FirebaseAuthManagerListener {

    private final SignInActivityI signInActivityI;
    private final FirebaseAuthManagerI firebaseAuthManagerI;

    public SignInPresenter(SignInActivityI signInActivityI, Activity activity){
        this.signInActivityI = signInActivityI;
        this.firebaseAuthManagerI = new FirebaseAuthManager(activity,this);
    }

    @Override
    public void showSignInUI() {
        firebaseAuthManagerI.showSignInUI();
    }

    @Override
    public void signInWithGoogle(int requestCode, Intent data){
        firebaseAuthManagerI.signInWithGoogle(requestCode, data);
    }

    @Override
    public void signInEmailAndPassword(String email, String password) {
        if (TextUtils.isEmpty(email.trim())){
            signInActivityI.etEmailSetError("Email cannot be empty");
            signInActivityI.etEmailRequestFocus();
        }else if (TextUtils.isEmpty(password.trim())){
            signInActivityI.etPasswordSetError("Password cannot be empty");
            signInActivityI.etPasswordRequestFocus();
        }else{
            firebaseAuthManagerI.signInEmailAndPassword(email, password);
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

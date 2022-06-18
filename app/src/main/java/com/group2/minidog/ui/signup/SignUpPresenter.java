package com.group2.minidog.ui.signup;

import android.app.Activity;
import android.text.TextUtils;

import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

public class SignUpPresenter implements SignUpPresenterI, FirebaseAuthManagerListener {

    private final SignUpActivityI signUpActivityI;
    private final FirebaseAuthManager firebaseAuthManager;

    public SignUpPresenter(SignUpActivityI signUpActivityI, Activity activity){
        this.signUpActivityI = signUpActivityI;
        this.firebaseAuthManager = new FirebaseAuthManager(activity, this);
    }

    @Override
    public void createUser(String email, String password, String rePassword) {
        if (TextUtils.isEmpty(email.trim())){
            signUpActivityI.etEmailSetError("Email cannot be empty");
            signUpActivityI.etEmailRequestFocus();
        }else if (TextUtils.isEmpty(password.trim())){
            signUpActivityI.etPasswordSetError("Password cannot be empty");
            signUpActivityI.etPasswordRequestFocus();
        }else if (TextUtils.isEmpty(rePassword.trim())){
            signUpActivityI.etRePasswordSetError("Password cannot be empty");
            signUpActivityI.etRePasswordRequestFocus();
        }else if(!password.equals(rePassword)){
            signUpActivityI.etPasswordSetError("");
            signUpActivityI.etRePasswordSetError("Passwords does not match");
        }else{
            firebaseAuthManager.signUpWithEmailAndPassword(email, password);
        }
    }

    @Override
    public void onSuccess() {
        signUpActivityI.showToast("Account created successfully.");
        signUpActivityI.goToSignInActivity();
    }

    @Override
    public void onFail(String message) {
        signUpActivityI.showToast(message);
    }
}

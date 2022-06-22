package com.group2.minidog.ui.main.profile;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;
import com.group2.minidog.network.App;
import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.firebase.FirebaseAuthManagerListener;

import javax.inject.Inject;

public class ProfilePresenter implements ProfilePresenterI, FirebaseAuthManagerListener {

    private final ProfileFragmentI profileFragmentI;
    private final FirebaseAuthManager firebaseAuthManager;
    @Inject
    public FirebaseUser firebaseUser;

    public ProfilePresenter(Activity activity, ProfileFragmentI profileFragmentI) {
        App.getAppComponent().inject(this);
        this.profileFragmentI = profileFragmentI;
        this.firebaseAuthManager = new FirebaseAuthManager(activity, this);
        onPresenterCreated();
    }

    private void onPresenterCreated() {
        profileFragmentI.initView();
        profileFragmentI.setValues(String.valueOf(firebaseUser.getPhotoUrl()), firebaseUser.getDisplayName(), firebaseUser.getEmail());
    }

    @Override
    public void signOut() {
        firebaseAuthManager.signOut();
        profileFragmentI.goToSignInActivity();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String message) {

    }
}

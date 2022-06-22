package com.group2.minidog.ui.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group2.minidog.databinding.ActivitySplashScreenBinding;
import com.group2.minidog.ui.main.MainActivity;
import com.group2.minidog.ui.signin.SignInActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity implements SplashScreenActivityI {

    private ActivitySplashScreenBinding binding;
    private SplashScreenPresenterI splashScreenPresenterI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        splashScreenPresenterI = new SplashScreenPresenter(this, this);
        splashScreenPresenterI.checkCurrentUser();
    }

    @Override
    public void goToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void goToSignInactivity(){
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}
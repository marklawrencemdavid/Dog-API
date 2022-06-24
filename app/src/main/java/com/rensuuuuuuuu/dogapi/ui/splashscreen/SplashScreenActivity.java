package com.rensuuuuuuuu.dogapi.ui.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rensuuuuuuuu.dogapi.databinding.ActivitySplashScreenBinding;
import com.rensuuuuuuuu.dogapi.ui.main.MainActivity;
import com.rensuuuuuuuu.dogapi.ui.signin.SignInActivity;

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
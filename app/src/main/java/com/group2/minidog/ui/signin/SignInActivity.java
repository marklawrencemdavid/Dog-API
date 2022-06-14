package com.group2.minidog.ui.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.group2.minidog.databinding.ActivitySignInBinding;
import com.group2.minidog.databinding.ActivitySplashScreenBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
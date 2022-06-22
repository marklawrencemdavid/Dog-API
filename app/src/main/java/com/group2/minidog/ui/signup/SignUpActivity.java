package com.group2.minidog.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group2.minidog.databinding.ActivitySignUpBinding;
import com.group2.minidog.ui.signin.SignInActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityI {

    private ActivitySignUpBinding binding;
    private SignUpPresenterI signUpPresenterI;
    private EditText etUsername, etEmail, etPassword, etRePassword;
    private ImageButton ibSignUp;
    private TextView txtSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signUpPresenterI = new SignUpPresenter(this, this);

        ibSignUp.setOnClickListener(view -> signUpPresenterI.createUser(etEmail.getText().toString(), etPassword.getText().toString(), etRePassword.getText().toString()));
        txtSignIn.setOnClickListener(view -> goToSignInActivity());
    }

    @Override
    public void initView() {
        etUsername = binding.etUsernameSignup;
        etEmail = binding.etEmailSignup;
        etPassword = binding.etPasswordSignup;
        etRePassword = binding.etRePasswordSignup;
        ibSignUp = binding.ibSignUp;
        txtSignIn = binding.txtSignIn;
    }

    @Override
    public void etEmailSetError(String error) {
        etEmail.setError(error);
    }

    @Override
    public void etPasswordSetError(String error) {
        etPassword.setError(error);
    }

    @Override
    public void etRePasswordSetError(String error) {
        etRePassword.setError(error);
    }

    @Override
    public void etEmailRequestFocus() {
        etEmail.requestFocus();
    }

    @Override
    public void etPasswordRequestFocus() {
        etPassword.requestFocus();
    }

    @Override
    public void etRePasswordRequestFocus() {
        etRePassword.requestFocus();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToSignInActivity(){
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        finish();
    }
}
package com.rensuuuuuuuu.dogapi.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rensuuuuuuuu.dogapi.databinding.ActivitySignInBinding;
import com.rensuuuuuuuu.dogapi.ui.main.MainActivity;
import com.rensuuuuuuuu.dogapi.ui.signup.SignUpActivity;
import com.rensuuuuuuuu.dogapi.util.LoadingOverlayDialog;

public class SignInActivity extends AppCompatActivity implements SignInActivityI {

    private ActivitySignInBinding binding;
    private SignInPresenterI signInPresenterI;
    private EditText etEmail, etPassword;
    private Button btnGoogleSignIn, btnSignIn;
    private TextView tvSignUp;
    private LoadingOverlayDialog loadingOverlayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        signInPresenterI = new SignInPresenter(this, this);

        btnSignIn.setOnClickListener(view ->
            signInPresenterI.signInEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
        );
        btnGoogleSignIn.setOnClickListener(view -> signInPresenterI.showSignInUI() );
        tvSignUp.setOnClickListener(view -> gotoSignUpActivity());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signInPresenterI.signInWithGoogle(requestCode, data);
    }

    @Override
    public void initView() {
        etEmail = binding.etEmailSignin;
        etPassword = binding.etPasswordSignin;
        btnSignIn = binding.btnSignIn;
        btnGoogleSignIn = binding.btnSignupGoogle;
        tvSignUp = binding.tvSignUp;
        loadingOverlayDialog = new LoadingOverlayDialog(this);
    }

    @Override
    public void etEmailSetError(String error) {
        etEmail.setError(error);
    }

    @Override
    public void etEmailRequestFocus() {
        etEmail.requestFocus();
    }

    @Override
    public void etPasswordSetError(String error) {
        etPassword.setError(error);
    }

    @Override
    public void etPasswordRequestFocus() {
        etPassword.requestFocus();
    }

    @Override
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOverlay() {
        loadingOverlayDialog.show();
    }

    @Override
    public void hideOverlay() {
        loadingOverlayDialog.hide();
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void gotoSignUpActivity() {
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }
}
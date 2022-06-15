package com.group2.minidog.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.group2.minidog.databinding.ActivitySignUpBinding;
import com.group2.minidog.ui.signin.SignInActivity;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private EditText etUsername, etEmail, etPassword, etRePassword;
    private ImageButton ibSignUp;
    private TextView txtSignIn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        firebaseAuth = FirebaseAuth.getInstance();

        ibSignUp.setOnClickListener(view -> createUser());
        txtSignIn.setOnClickListener(view -> goToSignInActivity());
    }

    private void initView() {
        etUsername = binding.etUsernameSignup;
        etEmail = binding.etEmailSignup;
        etPassword = binding.etPasswordSignup;
        etRePassword = binding.etRePasswordSignup;
        ibSignUp = binding.ibSignUp;
        txtSignIn = binding.txtSignIn;
    }

    private void createUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String rePassword = etRePassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
        }else if (TextUtils.isEmpty(rePassword)){
            etRePassword.setError("Password cannot be empty");
            etRePassword.requestFocus();
        }else if(!password.equals(rePassword)){
            etPassword.setError("");
            etRePassword.setError("Passwords does not match");
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        goToSignInActivity();
                    }else{
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void goToSignInActivity(){
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
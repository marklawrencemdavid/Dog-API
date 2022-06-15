package com.group2.minidog.ui.signin;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.group2.minidog.R;
import com.group2.minidog.databinding.ActivitySignInBinding;
import com.group2.minidog.ui.main.MainActivity;
import com.group2.minidog.ui.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private EditText etEmail, etPassword;
    private Button btnGoogleSignIn, btnSignUp;
    private ImageButton ibSignIn;

    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseAuth mAuth;

    private static final int REQ_ONE_TAP = 2;
    private boolean showOneTapUI = true;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        createGoogleSignInClient();
        mAuth = FirebaseAuth.getInstance();

        ibSignIn.setOnClickListener(view -> signInUser());
        btnGoogleSignIn.setOnClickListener(view -> signInGoogle());
        btnSignUp.setOnClickListener(view -> gotoSignUpActivity());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == RC_SIGN_IN) {
        //    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        //    try {
        //        // Google Sign In was successful, authenticate with Firebase
        //        GoogleSignInAccount account = task.getResult(ApiException.class);
        //        firebaseAuthWithGoogle(account.getIdToken());
        //    } catch (ApiException e) {
        //        // Google Sign In failed, update UI appropriately
        //        Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
        //    }
        //}
        switch (requestCode) {
            case REQ_ONE_TAP:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    String username = credential.getId();
                    String password = credential.getPassword();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate with your backend.
                        firebaseAuthWithGoogle(idToken);
                    } else {
                        Toast.makeText(this, "Couldn't get credential from result", Toast.LENGTH_SHORT).show();
                    }
                } catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case CommonStatusCodes.CANCELED:
                            Toast.makeText(this, "One-tap dialog was closed.", Toast.LENGTH_SHORT).show();
                            // Don't re-prompt the user.
                            showOneTapUI = false;
                            break;
                        case CommonStatusCodes.NETWORK_ERROR:
                            Toast.makeText(this, "One-tap encountered a network error.", Toast.LENGTH_SHORT).show();
                            // Try again or just ignore.
                            break;
                        default:
                            Toast.makeText(this, "Couldn't get credential from result."
                                    + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                break;
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    goToMainActivity();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(SignInActivity.this, "SignIn failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInGoogle() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult result) {
                        try {
                            startIntentSenderForResult(result.getPendingIntent().getIntentSender(), REQ_ONE_TAP, null, 0, 0, 0);
                        } catch (IntentSender.SendIntentException e) {
                            Toast.makeText(SignInActivity.this, "Couldn't start One Tap UI: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // No saved credentials found. Launch the One Tap sign-up flow, or
                        // do nothing and continue presenting the signed-out UI.
                        Toast.makeText(SignInActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signInUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignInActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    }else{
                        Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void initView() {
        etEmail = binding.etEmailSignin;
        etPassword = binding.etPasswordSignin;
        ibSignIn = binding.ibSignIn;
        btnGoogleSignIn = binding.btnSignupGoogle;
        btnSignUp = binding.btnSignUp;
    }

    private void createGoogleSignInClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
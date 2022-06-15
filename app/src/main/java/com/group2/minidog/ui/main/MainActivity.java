package com.group2.minidog.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group2.minidog.R;
import com.group2.minidog.databinding.ActivityMainBinding;
import com.group2.minidog.ui.signin.SignInActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button btn_logout;

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    private FirebaseAuth firebaseAuth;

    private SignInClient oneTapClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        createBottomNavigation();
        createGoogleSignInClient();
        firebaseAuth = FirebaseAuth.getInstance();

        checkCurrentUser();

        btn_logout.setOnClickListener(view -> signout());
    }

    private void initView() {
        btn_logout = binding.btnLogout;
    }

    private void createBottomNavigation() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fragment_home, R.id.fragment_save, R.id.fragment_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void createGoogleSignInClient() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        oneTapClient = Identity.getSignInClient(this);
    }

    private void checkCurrentUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            goToSignInActivity();
        }
    }

    private void signout() {
        firebaseAuth.signOut();
        oneTapClient.signOut();
        goToSignInActivity();
    }

    private void goToSignInActivity(){
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
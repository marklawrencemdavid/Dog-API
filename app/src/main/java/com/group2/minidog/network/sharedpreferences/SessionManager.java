package com.group2.minidog.network.sharedpreferences;

import android.content.SharedPreferences;

import com.group2.minidog.network.App;

import javax.inject.Inject;

public class SessionManager {
    @Inject
    public SharedPreferences sharedPreferences;

    public SessionManager() {
        App.getAppComponent().inject(this);
    }

    public String getUserEmail(){
        return sharedPreferences.getString("USER_EMAIL", null);
    }

    public void setUserEmail(String userEmail){ sharedPreferences.edit().putString("USER_EMAIL", userEmail).apply(); }

    public void  removeUserEmail(){ sharedPreferences.edit().remove("USER_EMAIL").apply(); }
}

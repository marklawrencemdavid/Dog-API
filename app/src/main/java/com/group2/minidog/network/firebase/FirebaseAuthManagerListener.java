package com.group2.minidog.network.firebase;

public interface FirebaseAuthManagerListener {
    void onSuccess();
    void onFail(String message);
}

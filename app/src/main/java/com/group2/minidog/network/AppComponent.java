package com.group2.minidog.network;

import com.group2.minidog.network.api.DogAPIManager;
import com.group2.minidog.network.daggermodules.AppModule;
import com.group2.minidog.network.daggermodules.NetworkModule;
import com.group2.minidog.network.firebase.FirebaseAuthManager;
import com.group2.minidog.network.sharedpreferences.SessionManager;
import com.group2.minidog.network.sqlite.DogDatabase;
import com.group2.minidog.ui.main.home.HomeDogItemBSDF;
import com.group2.minidog.ui.main.saves.SavesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    // Network
    void inject(DogAPIManager dogAPIManager);
    void inject(SessionManager sessionManager);
    void inject(DogDatabase dogDatabase);
    void inject(FirebaseAuthManager firebaseAuthManager);

    // BottomSheetDialogFragment
    void inject(HomeDogItemBSDF homeDogItemBSDF);

    // Presenter
    void inject(SavesPresenter savesPresenter);
}

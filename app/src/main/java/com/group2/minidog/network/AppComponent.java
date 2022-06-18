package com.group2.minidog.network;

import com.group2.minidog.network.daggermodules.AppModule;
import com.group2.minidog.network.daggermodules.NetworkModule;
import com.group2.minidog.ui.main.home.HomeModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(HomeModel homeModel);
}

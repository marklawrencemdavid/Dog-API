package com.group2.minidog.network;

import com.group2.minidog.network.modules.AppModule;
import com.group2.minidog.ui.main.home.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

}

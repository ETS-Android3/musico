package com.dalakoti07.android.musico.di.components;

import com.dalakoti07.android.musico.di.modules.AppSubComponents;
import com.dalakoti07.android.musico.di.modules.ContextModule;
import com.dalakoti07.android.musico.di.modules.RetrofitModule;
import com.dalakoti07.android.musico.di.modules.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, ContextModule.class, AppSubComponents.class, ViewModelFactoryModule.class})
public interface AppComponent {

    //exposing sub-components for activities and fragments
    MainComponent.Factory mainComponent();
    FragmentComponent.Factory fragmentComponent();
}

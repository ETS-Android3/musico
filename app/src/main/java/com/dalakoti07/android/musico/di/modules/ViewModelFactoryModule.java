package com.dalakoti07.android.musico.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.dalakoti07.android.musico.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}

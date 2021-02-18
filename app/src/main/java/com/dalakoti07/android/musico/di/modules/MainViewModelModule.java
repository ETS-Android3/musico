package com.dalakoti07.android.musico.di.modules;

import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.viewmodels.HomeScreenViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel.class)
    public abstract ViewModel bindLoginViewModel(HomeScreenViewModel viewModel);
}

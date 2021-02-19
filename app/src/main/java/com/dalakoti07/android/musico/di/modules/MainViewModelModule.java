package com.dalakoti07.android.musico.di.modules;

import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.viewmodels.AlbumDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.GenreDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.HomeScreenViewModel;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel.class)
    public abstract ViewModel bindHomeScreenViewModel(HomeScreenViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel.class)
    public abstract ViewModel bindAlbumDetailsViewModel(AlbumDetailsViewModel viewModel);
}

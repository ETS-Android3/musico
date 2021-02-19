package com.dalakoti07.android.musico.di.modules;

import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.viewmodels.GenreDetailsViewModel;
import com.dalakoti07.android.musico.viewmodels.HomeScreenViewModel;
import com.dalakoti07.android.musico.viewmodels.SharedListViewModel;
import com.dalakoti07.android.musico.viewmodels.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TabsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GenreDetailsViewModel.class)
    public abstract ViewModel bindGenreDetailsViewModel(GenreDetailsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SharedListViewModel.class)
    public abstract ViewModel bindSharedListViewModel(SharedListViewModel viewModel);

}

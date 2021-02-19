package com.dalakoti07.android.musico.di.components;

import android.content.Context;

import com.dalakoti07.android.musico.di.modules.ContextModule;
import com.dalakoti07.android.musico.di.modules.MainViewModelModule;
import com.dalakoti07.android.musico.di.modules.TabsViewModelModule;
import com.dalakoti07.android.musico.di.qualifier.ActivityContext;
import com.dalakoti07.android.musico.di.qualifier.ApplicationContext;
import com.dalakoti07.android.musico.di.scopes.FragmentScope;
import com.dalakoti07.android.musico.ui.fragment.AlbumListFragment;
import com.dalakoti07.android.musico.ui.fragment.ArtistListFragment;
import com.dalakoti07.android.musico.ui.fragment.GenreDetailFragment;
import com.dalakoti07.android.musico.ui.fragment.TracksListFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {TabsViewModelModule.class})
public interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory{
        FragmentComponent create();
    }

    void inject(GenreDetailFragment fragment);
    void inject(AlbumListFragment fragment);
    void inject(ArtistListFragment fragment);
    void inject(TracksListFragment fragment);
}

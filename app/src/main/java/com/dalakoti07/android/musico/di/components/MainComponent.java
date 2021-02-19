package com.dalakoti07.android.musico.di.components;

import com.dalakoti07.android.musico.di.modules.MainViewModelModule;
import com.dalakoti07.android.musico.di.scopes.ActivityScope;
import com.dalakoti07.android.musico.ui.activity.MainActivity;
import com.dalakoti07.android.musico.ui.fragment.AlbumDetailsFragment;
import com.dalakoti07.android.musico.ui.fragment.ArtistDetailFragment;
import com.dalakoti07.android.musico.ui.fragment.GenreDetailFragment;
import com.dalakoti07.android.musico.ui.fragment.MainFragment;
import com.dalakoti07.android.musico.viewmodels.AlbumDetailsViewModel;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {MainViewModelModule.class})
public interface MainComponent {

    @Subcomponent.Factory
    interface Factory{
        //todo pass context so that it can be re-used by every other fragment
        MainComponent create();
    }

    void inject(MainActivity activity);
    void inject(MainFragment fragment);
    void inject(GenreDetailFragment fragment);
    void inject(AlbumDetailsFragment fragment);
    void inject(ArtistDetailFragment fragment);
}

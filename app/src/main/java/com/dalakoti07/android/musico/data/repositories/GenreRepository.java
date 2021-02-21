package com.dalakoti07.android.musico.data.repositories;

import com.dalakoti07.android.musico.networks.MusicApiClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class GenreRepository {

    private MusicApiClient apiClient;

    @Inject
    public GenreRepository(MusicApiClient apiClient){
        this.apiClient=apiClient;
        Timber.d("created genre repository");
    }


}

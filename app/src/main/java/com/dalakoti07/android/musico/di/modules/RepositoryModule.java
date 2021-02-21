package com.dalakoti07.android.musico.di.modules;

import com.dalakoti07.android.musico.data.repositories.MainRepository;
import com.dalakoti07.android.musico.data.repositories.MainRepositoryContract;
import com.dalakoti07.android.musico.networks.MusicApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    MainRepositoryContract getMainRepository(MusicApiClient apiClient){
        return new MainRepository(apiClient);
    }
}

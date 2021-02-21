package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.repositories.MainRepositoryContract;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.networks.response.AllTracksResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AlbumDetailsViewModel extends ViewModel {
    private LiveData<AlbumDetailsResponse> albumDetailsResponseMutableLiveData;
    private MainRepositoryContract mainRepository;

    @Inject
    public AlbumDetailsViewModel(MainRepositoryContract mainRepository){
        Timber.d("created album details viewModel");
        this.mainRepository=mainRepository;
    }

    public LiveData<String> getApiError() {
        return mainRepository.getAlbumDetailsApiError();
    }

    public LiveData<AlbumDetailsResponse> getAlbumDetails(String artist, String album){
        if(albumDetailsResponseMutableLiveData==null){

            albumDetailsResponseMutableLiveData= mainRepository.fetchAlbumDetailsFromServer(artist,album);
        }
        return albumDetailsResponseMutableLiveData;
    }

}

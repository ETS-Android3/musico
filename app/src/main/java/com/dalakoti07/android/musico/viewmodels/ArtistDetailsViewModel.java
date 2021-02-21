package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.data.repositories.ArtistRepository;
import com.dalakoti07.android.musico.networks.response.ArtistDetailsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistTopAlbumsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistTopTrackResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ArtistDetailsViewModel extends ViewModel {
    private LiveData<ArtistDetailsResponse> artistDetailsResponses;
    private LiveData<String> artistDetailApiError=new MutableLiveData<>();

    private LiveData<List<AlbumModel>> artistTopAlbums;
    private LiveData<String> artistTopAlbumApiError;

    private LiveData<List<TrackModel>> artistTopTracks;
    private LiveData<String> artistTopTracksApiError=new MutableLiveData<>();

    private ArtistRepository artistRepository;

    @Inject
    public ArtistDetailsViewModel(ArtistRepository repository){
        this.artistRepository=repository;
        Timber.d("created artist details viewmodel");
    }

    public LiveData<String> getApiError() {
        if(artistDetailApiError!=null)
            return artistDetailApiError;
        artistDetailApiError=artistRepository.getArtistDetailApiError();
        return artistDetailApiError;
    }

    public LiveData<ArtistDetailsResponse> getArtistDetails(String artist){
        if(artistDetailsResponses!=null)
            return artistDetailsResponses;
        else
            return artistDetailsResponses= artistRepository.fetchArtistsFromServer(artist);
    }

    public LiveData<String> getArtistAlbumError(){
        return artistTopAlbumApiError;
    }

    public LiveData<List<AlbumModel>> getAlbums(String artist){
        if(artistTopAlbums==null)
            return artistTopAlbums= artistRepository.fetchAlbumsFromServerData(artist);
        return artistTopAlbums;
    }

    public LiveData<String> getArtistTrackError(){
        return artistTopTracksApiError;
    }

    public LiveData<List<TrackModel>> getTracks(String artist){
        if(artistTopTracks==null)
            return artistTopTracks= artistRepository.fetchTracksFromServerData(artist);
        return artistTopTracks;
    }

}

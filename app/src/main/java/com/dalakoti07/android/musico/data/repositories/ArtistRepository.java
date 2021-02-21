package com.dalakoti07.android.musico.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.ArtistDetailsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistTopAlbumsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistTopTrackResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class ArtistRepository {
    private MutableLiveData<ArtistDetailsResponse> artistDetailsResponses;
    private MutableLiveData<String> artistDetailApiError=new MutableLiveData<>();

    private MutableLiveData<List<AlbumModel>> artistTopAlbums;
    private MutableLiveData<String> artistTopAlbumApiError=new MutableLiveData<>();

    private MutableLiveData<List<TrackModel>> artistTopTracks;
    private MutableLiveData<String> artistTopTracksApiError=new MutableLiveData<>();


    private MusicApiClient apiClient;

    @Inject
    public ArtistRepository(MusicApiClient apiClient){
        this.apiClient=apiClient;
        Timber.d("created main repository");
    }

    public LiveData<String> getArtistDetailApiError() {
        return artistDetailApiError;
    }

    public LiveData<ArtistDetailsResponse> fetchArtistsFromServer(String artist) {
        artistDetailsResponses= new MutableLiveData<>();
        apiClient.getArtistDetails("artist.getinfo",artist).enqueue(new Callback<ArtistDetailsResponse>() {
            @Override
            public void onResponse(Call<ArtistDetailsResponse> call, Response<ArtistDetailsResponse> response) {
                if(response.isSuccessful()){
                    artistDetailsResponses.setValue(response.body());
                }else{
                    artistDetailApiError.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ArtistDetailsResponse> call, Throwable t) {
                artistDetailApiError.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return artistDetailsResponses;
    }

    public LiveData<String> getArtistAlbumError(){
        return artistTopAlbumApiError;
    }


    public LiveData<List<AlbumModel>> fetchAlbumsFromServerData(String artistName){
        artistTopAlbums= new MutableLiveData<>();
        apiClient.getArtistTopAlbums("artist.gettopalbums",artistName).enqueue(new Callback<ArtistTopAlbumsResponse>() {
            @Override
            public void onResponse(Call<ArtistTopAlbumsResponse> call, Response<ArtistTopAlbumsResponse> response) {
                if(response.isSuccessful()){
                    artistTopAlbums.setValue(response.body().getTopAlbumsWrapper().getAlbums());
                }else{
                    artistTopAlbumApiError.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ArtistTopAlbumsResponse> call, Throwable t) {
                artistTopAlbumApiError.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return artistTopAlbums;
    }


    public LiveData<String> getArtistTrackError(){
        return artistTopTracksApiError;
    }

    public LiveData<List<TrackModel>> fetchTracksFromServerData(String artistName){
        artistTopTracks= new MutableLiveData<>();
        apiClient.getArtistTopTracks("artist.gettoptracks",artistName).enqueue(new Callback<ArtistTopTrackResponse>() {
            @Override
            public void onResponse(Call<ArtistTopTrackResponse> call, Response<ArtistTopTrackResponse> response) {
                if(response.isSuccessful()){
                    artistTopTracks.setValue(response.body().getTopTrackWrapper().getMusicTags());
                }else{
                    artistTopTracksApiError.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ArtistTopTrackResponse> call, Throwable t) {
                artistTopTracksApiError.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return artistTopTracks;
    }
}

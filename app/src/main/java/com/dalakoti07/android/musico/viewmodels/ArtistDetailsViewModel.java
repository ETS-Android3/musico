package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.ArtistDetailsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistTopAlbumsResponse;
import com.dalakoti07.android.musico.networks.response.ArtistTopTrackResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ArtistDetailsViewModel extends ViewModel {
    private MutableLiveData<ArtistDetailsResponse> artistDetailsResponses;
    private MutableLiveData<String> artistDetailApiError=new MutableLiveData<>();

    private MutableLiveData<List<AlbumModel>> artistTopAlbums;
    private MutableLiveData<String> artistTopAlbumApiError=new MutableLiveData<>();

    private MutableLiveData<List<TrackModel>> artistTopTracks;
    private MutableLiveData<String> artistTopTracksApiError=new MutableLiveData<>();

    @Inject
    public MusicApiClient apiInterface;

    @Inject
    public ArtistDetailsViewModel(){
        Timber.d("created artist details viewmodel");
    }

    public LiveData<String> getApiError() {
        return artistDetailApiError;
    }

    public LiveData<ArtistDetailsResponse> getArtistDetails(String artist){
        if(artistDetailsResponses!=null)
            return artistDetailsResponses;
        else
            return fetchArtistsFromServer(artist);
    }

    private LiveData<ArtistDetailsResponse> fetchArtistsFromServer(String artist) {
        artistDetailsResponses= new MutableLiveData<>();
        apiInterface.getArtistDetails("artist.getinfo",artist).enqueue(new Callback<ArtistDetailsResponse>() {
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

    public LiveData<List<AlbumModel>> getAlbums(String artist){
        if(artistTopAlbums==null)
            return fetchAlbumsFromServerData(artist);
        return artistTopAlbums;
    }

    //todo if we use repository pattern then we these api calls code can be reused, but there is trade-off !
    private MutableLiveData<List<AlbumModel>> fetchAlbumsFromServerData(String artistName){
        artistTopAlbums= new MutableLiveData<>();
        apiInterface.getArtistTopAlbums("artist.gettopalbums",artistName).enqueue(new Callback<ArtistTopAlbumsResponse>() {
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

    public LiveData<List<TrackModel>> getTracks(String artist){
        if(artistTopTracks==null)
            return fetchTracksFromServerData(artist);
        return artistTopTracks;
    }

    private MutableLiveData<List<TrackModel>> fetchTracksFromServerData(String artistName){
        artistTopTracks= new MutableLiveData<>();
        apiInterface.getArtistTopTracks("artist.gettoptracks",artistName).enqueue(new Callback<ArtistTopTrackResponse>() {
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

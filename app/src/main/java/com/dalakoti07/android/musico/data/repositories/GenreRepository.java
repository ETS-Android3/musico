package com.dalakoti07.android.musico.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.AllArtistsResponse;
import com.dalakoti07.android.musico.networks.response.AllTracksResponse;
import com.dalakoti07.android.musico.networks.response.GenreAlbumsResponse;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class GenreRepository {

    private MutableLiveData<GenreDetailsResponse.MusicTag> musicWikiMutableLiveData;
    private MutableLiveData<String> musicWikiErrorMessage;

    private MutableLiveData<List<AlbumModel>> albumArrayList;
    private MutableLiveData<String> albumErrorMessage;

    private MutableLiveData<List<ArtistModel>> artistArrayList;
    private MutableLiveData<String> artistErrorMessage;

    private MutableLiveData<List<TrackModel>> trackArrayList;
    private MutableLiveData<String> tracksErrorMessage;

    private MusicApiClient apiClient;

    @Inject
    public GenreRepository(MusicApiClient apiClient){
        this.apiClient=apiClient;
        Timber.d("created genre repository");
    }

    public LiveData<String> getMusicWikiErrorData(){
        musicWikiErrorMessage=new MutableLiveData<>();
        return musicWikiErrorMessage;
    }


    public LiveData<GenreDetailsResponse.MusicTag> fetchMusicDetails(String genre) {
        musicWikiMutableLiveData=new MutableLiveData<>();
        apiClient.getGenreDetails("tag.getinfo",genre).enqueue(new Callback<GenreDetailsResponse>() {
            @Override
            public void onResponse(Call<GenreDetailsResponse> call, Response<GenreDetailsResponse> response) {
                if(response.isSuccessful()){
                    musicWikiMutableLiveData.setValue(response.body().getMusicTag());
                }else{
                    musicWikiErrorMessage.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<GenreDetailsResponse> call, Throwable t) {
                musicWikiErrorMessage.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return musicWikiMutableLiveData;
    }


    public LiveData<List<AlbumModel>> fetchAlbumsFromServer(String genre) {
        albumArrayList=new MutableLiveData<>();
        apiClient.getTopAlbumsInGenre("tag.gettopalbums",genre).enqueue(new Callback<GenreAlbumsResponse>() {
            @Override
            public void onResponse(Call<GenreAlbumsResponse> call, Response<GenreAlbumsResponse> response) {
                if(response.isSuccessful()){
                    albumArrayList.setValue(response.body().getAlbums().getAlbum());
                }else{
                    albumErrorMessage.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<GenreAlbumsResponse> call, Throwable t) {
                albumErrorMessage.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return albumArrayList;
    }

    public LiveData<String> getAlbumsError(){
        albumErrorMessage= new MutableLiveData<>();
        return albumErrorMessage;
    }


    public LiveData<List<ArtistModel>> fetchArtistsFromServer(String genre){
        artistArrayList=new MutableLiveData<>();
        apiClient.getTopArtistInGenre("tag.gettopartists",genre).enqueue(new Callback<AllArtistsResponse>() {
            @Override
            public void onResponse(Call<AllArtistsResponse> call, Response<AllArtistsResponse> response) {
                if(response.isSuccessful()){
                    artistArrayList.setValue(response.body().getTopartists().getArtist());
                }else{
                    artistErrorMessage.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<AllArtistsResponse> call, Throwable t) {
                artistErrorMessage.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return artistArrayList;
    }

    public LiveData<String> getArtistErrors(){
        artistErrorMessage= new MutableLiveData<>();
        return artistErrorMessage;
    }

    public LiveData<String> getTheTracksError(){
        tracksErrorMessage= new MutableLiveData<>();
        return tracksErrorMessage;
    }

    public LiveData<List<TrackModel>> fetchTracksFromServer(String genre) {
        trackArrayList= new MutableLiveData<>();
        apiClient.getTopTracksInGenre("tag.gettoptracks",genre).enqueue(new Callback<AllTracksResponse>() {
            @Override
            public void onResponse(Call<AllTracksResponse> call, Response<AllTracksResponse> response) {
                if(response.isSuccessful()){
                    trackArrayList.setValue(response.body().getTracks().getTrack());
                }else{
                    tracksErrorMessage.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<AllTracksResponse> call, Throwable t) {
                tracksErrorMessage.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return trackArrayList;
    }
}

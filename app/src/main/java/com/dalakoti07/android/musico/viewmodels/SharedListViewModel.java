package com.dalakoti07.android.musico.viewmodels;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.di.scopes.ActivityScope;
import com.dalakoti07.android.musico.di.scopes.FragmentScope;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.AllArtistsResponse;
import com.dalakoti07.android.musico.networks.response.AllTracksResponse;
import com.dalakoti07.android.musico.networks.response.GenreAlbumsResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * This viewModel is shared by AlbumListFragment,ArtistListFragment,TracksListFragment
 */
public class SharedListViewModel extends ViewModel {
    private MutableLiveData<List<AlbumModel>> albumArrayList;
    private final MutableLiveData<String> albumErrorMessage= new MutableLiveData<>();

    private MutableLiveData<List<ArtistModel>> artistArrayList;
    private MutableLiveData<String> artistErrorMessage= new MutableLiveData<>();

    private MutableLiveData<List<TrackModel>> trackArrayList;
    private MutableLiveData<String> tracksErrorMessage= new MutableLiveData<>();

    @Inject
    public MusicApiClient apiInterface;

    @Inject
    public SharedListViewModel(){
        Timber.d("shared list viewmodel created ");
    }

    public LiveData<List<AlbumModel>> getAlbumsList(String genre){
        if(albumArrayList!=null)
            return albumArrayList;
        else{
            return fetchAlbumsFromServer(genre);
        }
    }

    private LiveData<List<AlbumModel>> fetchAlbumsFromServer(String genre) {
        albumArrayList=new MutableLiveData<>();
        apiInterface.getTopAlbumsInGenre("tag.gettopalbums",genre).enqueue(new Callback<GenreAlbumsResponse>() {
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
        return albumErrorMessage;
    }


    public LiveData<List<ArtistModel>> getArtists(String genre){
        if(artistArrayList==null)
            return fetchArtistsFromServer(genre);
        else
            return artistArrayList;
    }

    private LiveData<List<ArtistModel>> fetchArtistsFromServer(String genre){
        artistArrayList=new MutableLiveData<>();
        apiInterface.getTopArtistInGenre("tag.gettopartists",genre).enqueue(new Callback<AllArtistsResponse>() {
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
        return artistErrorMessage;
    }

    public LiveData<List<TrackModel>> getTheTracks(String genre){
        if(trackArrayList==null)
            return fetchTracksFromServer(genre);
        else
            return trackArrayList;
    }

    public LiveData<String> getTheTracksError(){
        return tracksErrorMessage;
    }

    private LiveData<List<TrackModel>> fetchTracksFromServer(String genre) {
        trackArrayList= new MutableLiveData<>();
        apiInterface.getTopTracksInGenre("tag.gettoptracks",genre).enqueue(new Callback<AllTracksResponse>() {
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

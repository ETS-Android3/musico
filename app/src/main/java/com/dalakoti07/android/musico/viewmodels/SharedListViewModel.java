package com.dalakoti07.android.musico.viewmodels;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.data.models.ArtistModel;
import com.dalakoti07.android.musico.data.models.TrackModel;
import com.dalakoti07.android.musico.data.repositories.GenreRepository;
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
    private LiveData<List<AlbumModel>> albumArrayList;
    private LiveData<String> albumErrorMessage;

    private LiveData<List<ArtistModel>> artistArrayList;
    private LiveData<String> artistErrorMessage;

    private LiveData<List<TrackModel>> trackArrayList;
    private LiveData<String> tracksErrorMessage;

    public GenreRepository repository;

    @Inject
    public SharedListViewModel(GenreRepository repository){
        this.repository=repository;
        Timber.d("shared list viewmodel created ");
    }

    public LiveData<List<AlbumModel>> getAlbumsList(String genre){
        if(albumArrayList!=null)
            return albumArrayList;
        else{
            return albumArrayList= repository.fetchAlbumsFromServer(genre);
        }
    }

    public LiveData<String> getAlbumsError(){
        if(albumErrorMessage==null)
            return albumErrorMessage=repository.getAlbumsError();
        else
            return albumErrorMessage;
    }

    public LiveData<List<ArtistModel>> getArtists(String genre){
        if(artistArrayList==null)
            return artistArrayList=repository.fetchArtistsFromServer(genre);
        else
            return artistArrayList;
    }

    public LiveData<String> getArtistErrors(){
        if(artistErrorMessage==null)
            return artistErrorMessage=repository.getArtistErrors();
        else
            return artistErrorMessage;
    }

    public LiveData<List<TrackModel>> getTheTracks(String genre){
        if(trackArrayList==null)
            return trackArrayList= repository.fetchTracksFromServer(genre);
        else
            return trackArrayList;
    }

    public LiveData<String> getTheTracksError(){
        if(tracksErrorMessage==null)
            return tracksErrorMessage=repository.getTheTracksError();
        else
            return tracksErrorMessage;
    }

}

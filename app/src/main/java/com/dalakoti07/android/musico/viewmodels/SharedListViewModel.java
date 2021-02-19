package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.AlbumModel;
import com.dalakoti07.android.musico.networks.MusicApiClient;
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
    private MutableLiveData<String> errorMessage= new MutableLiveData<>();

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
                    errorMessage.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<GenreAlbumsResponse> call, Throwable t) {
                errorMessage.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return albumArrayList;
    }

}

package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.networks.response.AllTracksResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AlbumDetailsViewModel extends ViewModel {

    @Inject
    public MusicApiClient apiInterface;

    private MutableLiveData<AlbumDetailsResponse> albumDetailsResponseMutableLiveData;
    private MutableLiveData<String> apiError=new MutableLiveData<>();

    @Inject
    public AlbumDetailsViewModel(){
        Timber.d("created album details viewModel");
    }

    public LiveData<String> getApiError() {
        return apiError;
    }

    public LiveData<AlbumDetailsResponse> getAlbumDetails(String artist, String album){
        if(albumDetailsResponseMutableLiveData==null)
            return fetchAlbumDetailsFromServer(artist,album);
        return albumDetailsResponseMutableLiveData;
    }

    private LiveData<AlbumDetailsResponse> fetchAlbumDetailsFromServer(String artist, String album) {
        albumDetailsResponseMutableLiveData= new MutableLiveData<>();
        apiInterface.getAlbumDetails("album.getinfo",artist,album).enqueue(new Callback<AlbumDetailsResponse>() {
            @Override
            public void onResponse(Call<AlbumDetailsResponse> call, Response<AlbumDetailsResponse> response) {
                if(response.isSuccessful()){
                    albumDetailsResponseMutableLiveData.setValue(response.body());
                }else{
                    apiError.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<AlbumDetailsResponse> call, Throwable t) {
                apiError.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return albumDetailsResponseMutableLiveData;
    }
}

package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.ArtistDetailsResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ArtistDetailsViewModel extends ViewModel {
    private MutableLiveData<ArtistDetailsResponse> artistDetailsResponses;
    private MutableLiveData<String> apiError=new MutableLiveData<>();

    @Inject
    public MusicApiClient apiInterface;

    @Inject
    public ArtistDetailsViewModel(){
        Timber.d("created artist details viewmodel");
    }

    //todo error handling and add chips which show tags
    public LiveData<String> getApiError() {
        return apiError;
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
                    apiError.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ArtistDetailsResponse> call, Throwable t) {
                apiError.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return artistDetailsResponses;
    }
}

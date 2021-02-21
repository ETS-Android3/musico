package com.dalakoti07.android.musico.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.AllGenreResponse;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class MainRepository implements MainRepositoryContract {
    private MutableLiveData<ArrayList<SongGenre>> allSongGenres;
    private final MutableLiveData<String> allGenreErrorMessage= new MutableLiveData<>();

    private MusicApiClient apiClient;

    @Inject
    public MainRepository(MusicApiClient apiClient){
        this.apiClient=apiClient;
        Timber.d("created main repository");
    }

    @Override
    public LiveData<ArrayList<SongGenre>> fetchAllGenres() {
        allSongGenres= new MutableLiveData<>();
        apiClient.getAllGenres("tag.getTopTags")
                .enqueue(new Callback<AllGenreResponse>() {
                    @Override
                    public void onResponse(Call<AllGenreResponse> call, Response<AllGenreResponse> response) {
                        if(response.isSuccessful()){
                            Timber.d("success");
                            allSongGenres.setValue((ArrayList<SongGenre>) response.body().getToptags().getTag());
                        }else{
                            Timber.d("error message "+response.message());
                            allGenreErrorMessage.setValue(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<AllGenreResponse> call, Throwable t) {
                        Timber.d("failed message "+t.getLocalizedMessage());
                        allGenreErrorMessage.setValue(t.getLocalizedMessage());
                    }
                });
        return allSongGenres;    }

    @Override
    public LiveData<String> getGenreErrorMessage() {
        return allGenreErrorMessage;
    }

}

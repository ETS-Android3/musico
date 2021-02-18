package com.dalakoti07.android.musico.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.AllGenreResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeScreenViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SongGenre>> allSongGenres;
    private MutableLiveData<String> errorMessage= new MutableLiveData<>();

    @Inject
    public MusicApiClient apiInterface;

    @Inject
    public HomeScreenViewModel(){
        Timber.d("home screen viewModel created");
    }

    public LiveData<ArrayList<SongGenre>> getAllSongGenres(){
        if(allSongGenres==null){
            return fetchAllGenres();
        }else{
            return allSongGenres;
        }
    }

    private LiveData<ArrayList<SongGenre>> fetchAllGenres() {
        allSongGenres= new MutableLiveData<>();
        apiInterface.getAllGenres("tag.getTopTags",MusicApiClient.APIKEY_VALUE,MusicApiClient.FORMAT_VALUE)
                .enqueue(new Callback<AllGenreResponse>() {
                    @Override
                    public void onResponse(Call<AllGenreResponse> call, Response<AllGenreResponse> response) {
                        if(response.isSuccessful()){
                            Timber.d("success");
                            allSongGenres.setValue((ArrayList<SongGenre>) response.body().getToptags().getTag());
                        }else{
                            Timber.d("error message "+response.message());
                            errorMessage.setValue(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<AllGenreResponse> call, Throwable t) {
                        Timber.d("failed message "+t.getLocalizedMessage());
                        errorMessage.setValue(t.getLocalizedMessage());
                    }
                });
        return allSongGenres;
    }
}

package com.dalakoti07.android.musico.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.data.repositories.MainRepositoryContract;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;

import java.util.ArrayList;

public class FakeMainRepository implements MainRepositoryContract {

    private boolean throwGenreError=false;
    private MutableLiveData<String> genreApiError= new MutableLiveData<>();

    public void setThrowGenreError(boolean throwGenreError) {
        this.throwGenreError = throwGenreError;
    }

    @Override
    public LiveData<ArrayList<SongGenre>> fetchAllGenres() {
        if(throwGenreError){
            genreApiError.setValue("Unknown Error occurred");
            return new MutableLiveData<>();
        }else {
            ArrayList<SongGenre> songGenres=new ArrayList<>();
            songGenres.add(new SongGenre("pop",23,907));
            return new MutableLiveData<>(songGenres);
        }
    }

    @Override
    public LiveData<String> getGenreErrorMessage() {
        return genreApiError;
    }

    @Override
    public LiveData<AlbumDetailsResponse> fetchAlbumDetailsFromServer(String artist, String album) {
        return null;
    }

    @Override
    public LiveData<String> getAlbumDetailsApiError() {
        return null;
    }
}

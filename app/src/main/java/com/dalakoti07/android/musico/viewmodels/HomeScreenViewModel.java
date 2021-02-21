package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.data.repositories.MainRepositoryContract;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;

public class HomeScreenViewModel extends ViewModel {
    private LiveData<ArrayList<SongGenre>> allSongGenres;
    private LiveData<String> allGenreErrorMessage;

    private MainRepositoryContract mainRepository;

    @Inject
    public HomeScreenViewModel(MainRepositoryContract repository){
        Timber.d("home screen viewModel created");
        this.mainRepository=repository;
    }

    public LiveData<ArrayList<SongGenre>> getAllSongGenres(){
        if(allSongGenres==null){
            return allSongGenres=mainRepository.fetchAllGenres();
        }else{
            return allSongGenres;
        }
    }

    public LiveData<String> getErrorMessage(){
        if(allGenreErrorMessage==null)
            return allGenreErrorMessage= mainRepository.getGenreErrorMessage();
        return allGenreErrorMessage;
    }

}

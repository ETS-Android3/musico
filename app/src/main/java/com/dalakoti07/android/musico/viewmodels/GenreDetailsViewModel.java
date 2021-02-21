package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.data.repositories.GenreRepository;
import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class GenreDetailsViewModel extends ViewModel {

    private GenreRepository repository;

    @Inject
    public GenreDetailsViewModel(GenreRepository repository){
        this.repository=repository;
        Timber.d("genre details viewmodel created");
    }

    private LiveData<GenreDetailsResponse.MusicTag> musicWikiMutableLiveData;
    private LiveData<String> errorMessage;

    public LiveData<GenreDetailsResponse.MusicTag> getMusicWiki(String genre){
        if(musicWikiMutableLiveData!=null)
            return musicWikiMutableLiveData;
        else{
            return musicWikiMutableLiveData= repository.fetchMusicDetails(genre);
        }
    }

    public LiveData<String> getErrorData(){
        if(errorMessage==null)
            return errorMessage= repository.getMusicWikiErrorData();
        return errorMessage;
    }

}

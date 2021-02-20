package com.dalakoti07.android.musico.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dalakoti07.android.musico.networks.MusicApiClient;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class GenreDetailsViewModel extends ViewModel {

    @Inject
    public MusicApiClient apiInterface;

    @Inject
    public GenreDetailsViewModel(){
        Timber.d("genre details viewmodel created");
    }

    private MutableLiveData<GenreDetailsResponse.MusicTag> musicWikiMutableLiveData;
    private MutableLiveData<String> errorMessage= new MutableLiveData<>();

    public LiveData<GenreDetailsResponse.MusicTag> getMusicWiki(String genre){
        if(musicWikiMutableLiveData!=null)
            return musicWikiMutableLiveData;
        else{
            return fetchMusicDetails(genre);
        }
    }

    public LiveData<String> getErrorData(){
        return errorMessage;
    }

    private LiveData<GenreDetailsResponse.MusicTag> fetchMusicDetails(String genre) {
        musicWikiMutableLiveData=new MutableLiveData<>();
        apiInterface.getGenreDetails("tag.getinfo",genre).enqueue(new Callback<GenreDetailsResponse>() {
            @Override
            public void onResponse(Call<GenreDetailsResponse> call, Response<GenreDetailsResponse> response) {
                if(response.isSuccessful()){
                    musicWikiMutableLiveData.setValue(response.body().getMusicTag());
                }else{
                    errorMessage.setValue("Code:"+response.code()+" msg: "+response.message());
                    Timber.d("Code:"+response.code()+" msg: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<GenreDetailsResponse> call, Throwable t) {
                errorMessage.setValue("Failed: "+t.getLocalizedMessage());
                Timber.d("Failed: %s", t.getLocalizedMessage());
            }
        });
        return musicWikiMutableLiveData;
    }
}

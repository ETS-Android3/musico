package com.dalakoti07.android.musico.data.repositories;

import androidx.lifecycle.LiveData;

import com.dalakoti07.android.musico.data.models.SongGenre;
import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;

import java.util.ArrayList;

public interface MainRepositoryContract {

    LiveData<ArrayList<SongGenre>> fetchAllGenres();
    LiveData<String> getGenreErrorMessage();

    LiveData<AlbumDetailsResponse> fetchAlbumDetailsFromServer(String artist, String album);
    LiveData<String> getAlbumDetailsApiError();
}

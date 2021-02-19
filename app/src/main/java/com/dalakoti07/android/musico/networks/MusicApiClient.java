package com.dalakoti07.android.musico.networks;

import com.dalakoti07.android.musico.networks.response.AlbumDetailsResponse;
import com.dalakoti07.android.musico.networks.response.AllArtistsResponse;
import com.dalakoti07.android.musico.networks.response.AllGenreResponse;
import com.dalakoti07.android.musico.networks.response.AllTracksResponse;
import com.dalakoti07.android.musico.networks.response.GenreAlbumsResponse;
import com.dalakoti07.android.musico.networks.response.GenreDetailsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MusicApiClient {
    String METHOD = "method";

    // apiKey and format are added by interceptor interceptor

    @GET("2.0/")
    Call<AllGenreResponse> getAllGenres(@Query(METHOD) String method);

    @GET("2.0/")
    Call<GenreDetailsResponse> getGenreDetails(@Query(METHOD) String method,@Query("tag") String genre);

    @GET("2.0")
    Call<GenreAlbumsResponse> getTopAlbumsInGenre(@Query(METHOD) String method,@Query("tag")String genre);

    @GET("2.0")
    Call<AllArtistsResponse> getTopArtistInGenre(@Query(METHOD) String method, @Query("tag")String genre);

    @GET("2.0")
    Call<AllTracksResponse> getTopTracksInGenre(@Query(METHOD) String method, @Query("tag")String genre);

    @GET("2.0")
    Call<AlbumDetailsResponse> getAlbumDetails(@Query(METHOD) String method, @Query("artist") String artist, @Query("album") String album);
}

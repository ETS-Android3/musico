package com.dalakoti07.android.musico.networks;

import com.dalakoti07.android.musico.networks.response.AllGenreResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MusicApiClient {
    String METHOD = "method";
    String APIKEY = "api_key";
    String APIKEY_VALUE = "7c5701c72b926f5d1e5b209aadd3aedc";
    String FORMAT = "format";
    String FORMAT_VALUE = "json";

    //todo put apiKey and format in interceptor

    @GET("2.0/")
    Call<AllGenreResponse> getAllGenres(@Query(METHOD) String method,
                                        @Query(APIKEY) String key,
                                        @Query(FORMAT) String format);


}

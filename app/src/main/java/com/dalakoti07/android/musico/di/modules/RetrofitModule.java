package com.dalakoti07.android.musico.di.modules;

import android.preference.PreferenceManager;

import com.dalakoti07.android.musico.networks.MusicApiClient;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class RetrofitModule {
    private final String APIKEY = "api_key";
    private final String APIKEY_VALUE = "7c5701c72b926f5d1e5b209aadd3aedc";
    private final String FORMAT = "format";
    private final String FORMAT_VALUE = "json";

    @Provides
    @Singleton
    MusicApiClient getApiInterface(Retrofit retroFit) {
        Timber.d("creating api service from retrofit instance");
        return retroFit.create(MusicApiClient.class);
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public @NotNull Response intercept(@NotNull Chain chain) throws IOException {
                        Request.Builder originalRequest = chain.request().newBuilder();
                        HttpUrl originalHttpUrl=chain.request().url();
                        HttpUrl newHttpUrl= originalHttpUrl.newBuilder().addQueryParameter(APIKEY,APIKEY_VALUE)
                                .addQueryParameter(FORMAT,FORMAT_VALUE)
                                .build();
                        originalRequest.url(newHttpUrl);
                        return chain.proceed(originalRequest.build());
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}

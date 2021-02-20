package com.dalakoti07.android.musico;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.dalakoti07.android.musico.di.components.AppComponent;
import com.dalakoti07.android.musico.di.components.DaggerAppComponent;
import com.dalakoti07.android.musico.di.modules.ContextModule;
import com.dalakoti07.android.musico.di.modules.RetrofitModule;

import timber.log.Timber;

public class MusicApplication extends Application {
    AppComponent appComponent;
    private static String packageName;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        appComponent= DaggerAppComponent.builder().retrofitModule(new RetrofitModule())
                .contextModule(new ContextModule(this))
                .build();
        packageName=this.getPackageName();
    }

    public  static String getApplicationPackageName(){
        return packageName;
    }

    public static MusicApplication get(Activity activity){
        return (MusicApplication) activity.getApplication();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}

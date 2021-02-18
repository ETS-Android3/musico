package com.dalakoti07.android.musico.di.modules;

import android.content.Context;

import com.dalakoti07.android.musico.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context){
        this.context=context;
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context provideContext(){
        return context;
    }
}

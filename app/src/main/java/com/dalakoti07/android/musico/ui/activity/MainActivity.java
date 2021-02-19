package com.dalakoti07.android.musico.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dalakoti07.android.musico.MusicApplication;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.di.components.MainComponent;

public class MainActivity extends AppCompatActivity {
    public MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent= MusicApplication.get(this).getApplicationComponent().mainComponent().create();
        mainComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
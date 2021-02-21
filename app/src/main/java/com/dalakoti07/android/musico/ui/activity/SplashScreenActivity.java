package com.dalakoti07.android.musico.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dalakoti07.android.musico.MusicApplication;
import com.dalakoti07.android.musico.R;
import com.dalakoti07.android.musico.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    private final static int TIME_OUT_TIME=3000;
    private Animation topAnimation,bottomAnimation;
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        startAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.next_screen_move_in,R.anim.next_screen_move_out);
                finish();
            }
        },TIME_OUT_TIME);
        MusicApplication.get(this).getApplicationComponent().inject(this);
    }

    private void startAnimation() {
        binding.ivLogo.setAnimation(topAnimation);
        binding.tvName.setAnimation(bottomAnimation);
        binding.tvTagline.setAnimation(bottomAnimation);
    }

}
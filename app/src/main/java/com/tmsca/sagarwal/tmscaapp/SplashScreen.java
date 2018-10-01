package com.tmsca.sagarwal.tmscaapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tmsca.sagarwal.tmscaapp.user.LoginActivity;

import java.lang.reflect.UndeclaredThrowableException;

public class SplashScreen extends AppCompatActivity{
    ImageView splash;
    ProgressBar progressBar;
    Animation splashAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splash = findViewById(R.id.splash);
        splashAnimation = AnimationUtils.loadAnimation(this, R.anim.fui_slide_out_left);
        splashAnimation.start();
        splash.setAnimation(splashAnimation);
        progressBar = findViewById(R.id.progressBar);

        ObjectAnimator progressAnimation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimation.setDuration(4000);
        progressAnimation.setInterpolator(new DecelerateInterpolator());
        progressAnimation.start();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, StartActivity.class));
                finish();
            }
        }, 3000);

    }
}

package com.mackenzie.documentalia03.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mackenzie.documentalia03.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private TimerTask task;
    private Animation anim;
    private ImageView imageView;
    private static final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        imageView = findViewById(R.id.logo);
        SplashTimer();
        animacion();

    }

    public void animacion() {
        anim = AnimationUtils.loadAnimation(this, R.anim.transaparicion);
        anim.setFillAfter(true);
        imageView.startAnimation(anim);

    }

    public void SplashTimer() {
        task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_DELAY);
    }
}
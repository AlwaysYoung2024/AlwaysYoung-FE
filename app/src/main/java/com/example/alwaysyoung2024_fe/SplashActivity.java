package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // 2초

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // 항상 OnboardingActivity로 이동
                Intent onboarding = new Intent(SplashActivity.this, OnboardingActivity.class);
                startActivity(onboarding);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

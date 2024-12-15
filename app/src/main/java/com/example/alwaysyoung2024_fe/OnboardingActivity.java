package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import me.relex.circleindicator.CircleIndicator3; // CircleIndicator3로 변경

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private OnboardingAdapter adapter;
    private Button startButton;  // 시작하기 버튼 추가
    private CircleIndicator3 indicator;  // CircleIndicator3 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);  // CircleIndicator3 인스턴스 찾기

        // 온보딩 페이지 레이아웃 목록
        List<Integer> layouts = new ArrayList<>();
        layouts.add(R.layout.onboarding_page1);
        layouts.add(R.layout.onboarding_page2);
        layouts.add(R.layout.onboarding_page3);
        layouts.add(R.layout.onboarding_page4);
        layouts.add(R.layout.onboarding_page5);

        adapter = new OnboardingAdapter(this, layouts);
        viewPager.setAdapter(adapter);

        // CircleIndicator3와 ViewPager2 연동
        indicator.setViewPager(viewPager);  // CircleIndicator3는 setViewPager2 메서드 사용

        // 페이지 변경 리스너 설정
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 마지막 페이지에 버튼 추가
                if (position == layouts.size() - 1) {
                    setupStartButton();  // 시작하기 버튼 설정
                } else {
                    hideStartButton(); // 마지막 페이지가 아닐 때 버튼 숨김
                }
            }
        });
    }

    private void setupStartButton() {
        // 시작하기 버튼을 찾고 클릭 리스너 설정
        startButton = findViewById(R.id.goto_login);
        if (startButton != null) {
            // Handler를 사용하여 1초 지연 후 버튼을 보이게 설정
            new Handler().postDelayed(() -> {
                startButton.setVisibility(View.VISIBLE); // 버튼 보이기
                startButton.setOnClickListener(v -> {
                    // LoginActivity로 이동
                    Intent loginIntent = new Intent(OnboardingActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                });
            }, 1000); // 1초 후 실행
        }
    }

    private void hideStartButton() {
        if (startButton != null) {
            startButton.setVisibility(View.GONE); // 버튼 숨기기
        }
    }
}

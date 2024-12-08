package com.example.alwaysyoung2024_fe;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // 1. PaperOnboardingPage 준비
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Hotels",
                "All hotels and hostels are sorted by hospitality rating",
                Color.parseColor("#678FB4"), R.drawable.hotel, R.drawable.key);

        PaperOnboardingPage scr2 = new PaperOnboardingPage("Banks",
                "We carefully verify all banks before adding them into the app",
                Color.parseColor("#65B0B4"), R.drawable.bank, R.drawable.wallet);

        PaperOnboardingPage scr3 = new PaperOnboardingPage("Stores",
                "All local stores are categorized for your convenience",
                Color.parseColor("#9B90BC"), R.drawable.store, R.drawable.cart);

        // 2. 슬라이드 목록 설정
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        // 3. PaperOnboardingFragment 추가
        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.containers, onBoardingFragment);
        fragmentTransaction.commit();

        // 4. 온보딩 완료 시 OnboardingFragment로 전환
        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                OnboardingFragment nextFragment = new OnboardingFragment();
                fragmentTransaction.replace(R.id.containers, nextFragment);
                fragmentTransaction.commit();
            }
        });
    }
}

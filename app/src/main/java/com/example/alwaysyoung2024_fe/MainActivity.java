package com.example.alwaysyoung2024_fe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 초기화 시 기본 프래그먼트를 HomeFragment로 설정
        if (savedInstanceState == null) {
            handleFragmentNavigation(getIntent()); // Intent 확인 후 초기 Fragment 설정
        }

        // 네비게이션 아이템 선택 리스너
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.page_home) {
                    // 홈 화면 프래그먼트
                    transferTo(new HomeFragment());
                    return true;
                }

                if (itemId == R.id.page_today) {
                    // 오늘의 약 프래그먼트
                    transferTo(new TodayFragment());
                    return true;
                }

                if (itemId == R.id.page_records) {
                    // 기록 관리 프래그먼트
                    transferTo(new RecordsFragment());
                    return true;
                }

                if (itemId == R.id.page_profile) {
                    // 내 정보 프래그먼트
                    transferTo(new ProfileFragment());
                    return true;
                }

                return false;
            }
        });

        // 아이템을 다시 선택했을 때의 리스너 (여기서는 별도 동작 없음)
        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                // 중복 선택 시 특별한 동작 없음
            }
        });
    }

    // 프래그먼트를 전환하는 메서드
    private void transferTo(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // AddSuccessActivity에서 돌아왔을 때의 Fragment 전환 처리
    @Override
    protected void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
        handleFragmentNavigation(intent); // 새 Intent 확인
    }

    private void handleFragmentNavigation(android.content.Intent intent) {
        if (intent != null && "TodayFragment".equals(intent.getStringExtra("navigateTo"))) {
            // TodayFragment로 이동
            transferTo(new TodayFragment());
        } else {
            // 기본적으로 HomeFragment를 표시
            transferTo(new HomeFragment());
        }
    }
}

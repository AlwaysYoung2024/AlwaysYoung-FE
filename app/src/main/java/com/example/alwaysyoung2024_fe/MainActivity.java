package com.example.alwaysyoung2024_fe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


//    https://material.io/components/bottom-navigation/android#using-bottom-navigation
//    https://developer.android.com/training/basics/fragments/pass-data-between?hl=ko#java
//    https://developer.android.com/guide/fragments/fragmentmanager?hl=ko
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 초기화 시 기본 프래그먼트를 HomeFragment로 설정
        transferTo(HomeFragment.newInstance("param1", "param2"));

        // 네비게이션 아이템 선택 리스너
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.page_home) {
                    // 홈 화면 프래그먼트
                    transferTo(HomeFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_today) {
                    // 오늘의 약 프래그먼트
                    transferTo(TodayFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_records) {
                    // 기록 관리 프래그먼트
                    transferTo(RecordsFragment.newInstance("param1", "param2"));
                    return true;
                }

                if (itemId == R.id.page_profile) {
                    // 내 정보 프래그먼트
                    transferTo(ProfileFragment.newInstance("param1", "param2"));
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
}

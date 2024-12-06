package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class AddMedicineActivity extends AppCompatActivity {

    private Button nextButton; // "다음" 버튼 참조

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_medicine);

        // WindowInsets 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 초기 프래그먼트 로드
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddMedicine_NameFragment())
                    .commit();
        }

        // "다음" 버튼 초기화
        nextButton = findViewById(R.id.button_add_medicine);
        nextButton.setOnClickListener(v -> onNextButtonClicked());

        // 뒤로가기 버튼 클릭 이벤트
        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed()); // 현재 Activity에서 뒤로가기 처리
    }

    private void onNextButtonClicked() {
        // 현재 프래그먼트를 가져와서 해당 프래그먼트의 validateAndProceed 메서드 호출
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof AddMedicine_NameFragment) {
            ((AddMedicine_NameFragment) currentFragment).validateAndProceed(); // 약 이름 입력 확인 후 진행
        } else if (currentFragment instanceof AddMedicine_FrequencyFragment) {
            ((AddMedicine_FrequencyFragment) currentFragment).validateAndProceed(); // 복용 빈도 확인 후 진행
        } else if (currentFragment instanceof AddMedicine_TimingFragment) {
            ((AddMedicine_TimingFragment) currentFragment).validateAndProceed(); // 복용 시간 확인 후 진행
        }
    }

    // SuccessActivity로 이동
    public void navigateToAddSuccessActivity() {
        // AddMedicineActivity 종료 후 AddSuccessActivity로 이동
        Intent intent = new Intent(this, AddSuccessActivity.class);
        startActivity(intent);
        finish(); // 현재 AddMedicineActivity 종료
    }
}

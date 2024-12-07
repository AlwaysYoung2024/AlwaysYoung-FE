package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddSuccess2ctivity extends AppCompatActivity {

    private Button confirmButton; // '확인' 버튼 참조

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_success2);

        confirmButton = findViewById(R.id.button_add_schedule);
        confirmButton.setOnClickListener(v -> onConfirmButtonClicked());
    }

    private void onConfirmButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("navigateTo", "TodayFragment"); // TodayFragment로 이동 요청
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // 기존 MainActivity를 재사용하고 위로 쌓인 AddMedicineActivity 제거
        startActivity(intent);
        finish(); // AddSuccessActivity 종료
    }
}

package com.example.alwaysyoung2024_fe;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DayRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_record);

        // 뒤로가기 버튼 클릭시
        LinearLayout backButtonContainer = findViewById(R.id.backButtonContainer);
        backButtonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView dateTextView = findViewById(R.id.selectedDate);

        String selectedDate = getIntent().getStringExtra("selectedDate");
        dateTextView.setText(String.format("%s",selectedDate));

        TextView datestateText = findViewById(R.id.dayStateText);
        datestateText.setText(String.format("%s 현황",selectedDate));

    }
}
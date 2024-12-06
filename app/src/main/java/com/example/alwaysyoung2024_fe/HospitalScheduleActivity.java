package com.example.alwaysyoung2024_fe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HospitalScheduleActivity extends AppCompatActivity {

    private TextView dateText;
    private RelativeLayout datePickerContainer;
    private RelativeLayout hospitalScheduleContainer;
    private Calendar calendar;

    private ArrayList<String> hospitalSchedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_schedule);

        // 뒤로가기 버튼 클릭시
        LinearLayout backButtonContainer = findViewById(R.id.backButtonContainer);
        backButtonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //조회할 "월" 선택
        datePickerContainer = findViewById(R.id.datePickerContainer);
        datePickerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            } //클릭하면 타임피커 뜸
        });

        dateText = findViewById(R.id.dateText);
        hospitalScheduleContainer = findViewById(R.id.hospitalScheduleContainer);
        calendar = Calendar.getInstance();


        Intent intent = getIntent();
        String scheduleDate = intent.getStringExtra("HOSPITAL_SCHEDULE_DATE");

        if (scheduleDate == null || scheduleDate.isEmpty()) {
            scheduleDate = "2024년 10월"; // 기본 값 설정
        }

        // 병원 일정 리스트 초기화 (더미 데이터 추가)
        hospitalSchedules = new ArrayList<>();
        hospitalSchedules.add("2024년 10월 2일 서울대병원 진료");
        hospitalSchedules.add("2024년 10월 8일 강남세브란스 신경외과");
        hospitalSchedules.add("2024년 11월 15일 삼성서울병원 검진");


        updateDateText();
        updateSchedules();


    }

    private void showDatePickerDialog(){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        MonthYearPickerDialog dialog = new MonthYearPickerDialog(
                this,
                year,
                month,
                (selectedYear, selectedMonth) -> {
                    calendar.set(Calendar.YEAR, selectedYear);
                    calendar.set(Calendar.MONTH, selectedMonth-1);
                    updateDateText(); //선택한 날짜 반영
                    updateSchedules(); //선택한 월에 대한 병원 일정 업데이트
                }
        );
        dialog.show();
    }

    //사용자가 날짜를 선택하면 선택된 날짜가 나옴
    private void updateDateText(){
        //현재 연도와 월 가져오기
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String dateTextFormated = year + "년 " + month + "월";
        dateText.setText(dateTextFormated);
    }

    private void updateSchedules(){
        hospitalScheduleContainer.removeAllViews(); //기존 일정 삭제

        String currentMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREAN).format(calendar.getTime());

        // 예시 병원 일정 데이터
        ArrayList<String> hospitalSchedules = new ArrayList<>();
        hospitalSchedules.add("2024년 10월| 10월 2일 월요일 18:00 | 서울대병원 기관지");
        hospitalSchedules.add("2024년 10월| 10월 8일 수요일 20:00 | 서울대병원 기관지");
        hospitalSchedules.add("2024년 10월| 10월 21일 토요일 12:00 | 세브란스병원 신경외과");
        hospitalSchedules.add("2024년 11월| 11월 21일 토요일 12:00 | 세브란스병원 신경외과");


        // 마지막으로 추가된 뷰를 추적하기 위한 변수
        View previousView = null;
        boolean isFiretSchedule = true;

        //일정추가
        for(String schedule : hospitalSchedules){
            if (schedule.startsWith(currentMonth)) {

                // 일정 뷰 생성
                RelativeLayout scheduleView = (RelativeLayout) LayoutInflater.from(this)
                        .inflate(R.layout.hospital_schedule_item, hospitalScheduleContainer, false);

                TextView dateView = scheduleView.findViewById(R.id.schedule_date);
                TextView placeView = scheduleView.findViewById(R.id.schedule_place);

                String[] parts = schedule.split("\\|");
                dateView.setText(parts[1].trim());
                placeView.setText(parts[2].trim());

                // 레이아웃 파라미터 생성
                RelativeLayout.LayoutParams scheduleParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                if (isFiretSchedule) {
                    scheduleParams.setMargins(0,0,0,0);
                    isFiretSchedule = false;
                } else{
                    // 이전 뷰 아래에 배치
                    scheduleParams.addRule(RelativeLayout.BELOW, previousView.getId());
                    scheduleParams.setMargins(0, 20, 0, 0); // 위에서 20dp 간격 추가
                }



                // 고유 ID를 설정하여 참조 가능하게 함
                scheduleView.setId(View.generateViewId());
                scheduleView.setLayoutParams(scheduleParams);

                // 컨테이너에 추가
                hospitalScheduleContainer.addView(scheduleView);

                // 구분선 추가
                View divider = new View(this);
                RelativeLayout.LayoutParams dividerParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 2
                );

                dividerParams.addRule(RelativeLayout.BELOW, scheduleView.getId()); // `scheduleView` 아래에 배치
                dividerParams.setMargins(50, 20, 50, 0); // 간격 추가
                divider.setLayoutParams(dividerParams);
                divider.setBackgroundColor(getResources().getColor(R.color.main_color));
                divider.setId(View.generateViewId()); // 고유 ID 설정

                hospitalScheduleContainer.addView(divider);

                // 이전 뷰를 업데이트
                previousView = divider;
            }
        }
    }
}
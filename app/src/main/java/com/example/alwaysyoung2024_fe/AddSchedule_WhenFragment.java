package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddSchedule_WhenFragment extends Fragment {

    private DatePicker datePicker;
    private TimePicker timePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_schedule_when, container, false);

        // DatePicker와 TimePicker 초기화
        datePicker = view.findViewById(R.id.datePicker);
        timePicker = view.findViewById(R.id.timePicker);

        return view;
    }

    public void validateAndProceed() {
        // 날짜와 시간 유효성 검사를 추가할 수도 있음
        Toast.makeText(requireContext(), "진료 시간이 저장되었습니다.", Toast.LENGTH_SHORT).show();

        // AddSuccessActivity로 이동
        navigateToAddSuccessActivity();
    }

    private void navigateToAddSuccessActivity() {
        Intent intent = new Intent(requireActivity(), AddSuccessActivity.class);
        startActivity(intent);
        requireActivity().finish(); // AddScheduleActivity 종료
    }
}

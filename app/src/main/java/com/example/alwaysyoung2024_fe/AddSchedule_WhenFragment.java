package com.example.alwaysyoung2024_fe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddSchedule_WhenFragment extends Fragment {

    private Button datePickerButton;
    private TextView selectedDateTimeText;  // TextView to display the selected date and time
    private String selectedDateTime = ""; // 선택된 날짜 및 시간을 저장할 변수

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_schedule_when, container, false);
        datePickerButton = view.findViewById(R.id.datePickerButton);
        selectedDateTimeText = view.findViewById(R.id.selectedDateTimeText); // TextView 참조 초기화
        datePickerButton.setOnClickListener(v -> showDateTimePicker());
        return view;
    }

    private void showDateTimePicker() {
        AddSchedule_TimePickerFragment timePickerFragment = new AddSchedule_TimePickerFragment();
        timePickerFragment.setTargetFragment(this, 0);
        timePickerFragment.show(getParentFragmentManager(), "dateTimePicker");
    }

    public void onDateTimeSet(String selectedDateTime) {
        this.selectedDateTime = selectedDateTime; // 선택된 날짜 및 시간 저장
        selectedDateTimeText.setText(selectedDateTime); // Update the TextView with the selected date and time
    }

    public void validateAndProceed() {
        if (selectedDateTime.isEmpty()) {
            Toast.makeText(getContext(), "날짜와 시간을 선택해주세요.", Toast.LENGTH_LONG).show();
        } else {
            navigateToAddSuccessActivity(); // If date and time are set, proceed to the next activity
        }
    }

    private void navigateToAddSuccessActivity() {
        ((AddScheduleActivity) getActivity()).navigateToAddSuccessActivity();
    }
}

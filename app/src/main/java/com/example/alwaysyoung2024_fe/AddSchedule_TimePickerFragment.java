package com.example.alwaysyoung2024_fe;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddSchedule_TimePickerFragment extends DialogFragment {

    private NumberPicker dayPicker;
    private NumberPicker monthPicker;
    private NumberPicker yearPicker;
    private Button confirmButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true); // 다이얼로그 바깥 터치시 종료 설정

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_schedule_time_picker, null);

        // 날짜 선택: 일, 월, 년도 설정
        dayPicker = view.findViewById(R.id.day_picker);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);

        monthPicker = view.findViewById(R.id.month_picker);
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);

        yearPicker = view.findViewById(R.id.year_picker);
        yearPicker.setMinValue(2022);
        yearPicker.setMaxValue(2030);

        // 확인 버튼 설정
        confirmButton = view.findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            int day = dayPicker.getValue();
            int month = monthPicker.getValue();
            int year = yearPicker.getValue();

            // 전달할 데이터를 구성
            String selectedDate = year + "년 " + month + "월 " + day + "일";

            if (getTargetFragment() instanceof AddSchedule_WhenFragment) {
                ((AddSchedule_WhenFragment) getTargetFragment()).setDate(selectedDate);
            }

            dismiss(); // 다이얼로그 종료
        });

        // 다이얼로그 취소 시 동작 설정
        dialog.setOnCancelListener(dialog1 -> {
            if (getTargetFragment() instanceof AddSchedule_WhenFragment) {
                ((AddSchedule_WhenFragment) getTargetFragment()).clearDateSelection();
            }
        });

        dialog.setContentView(view);
        return dialog;
    }
}

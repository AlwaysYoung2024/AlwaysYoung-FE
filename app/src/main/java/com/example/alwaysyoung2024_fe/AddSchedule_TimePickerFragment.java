package com.example.alwaysyoung2024_fe;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddSchedule_TimePickerFragment extends DialogFragment {

    private NumberPicker yearPicker, monthPicker, dayPicker, hourPicker, minutePicker;
    private Button confirmButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_schedule_time_picker, null, false);

        initializePickers(view);
        confirmButton = view.findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> confirmDateTime());

        dialog.setContentView(view);
        return dialog;
    }

    private void initializePickers(View view) {
        // 현재 날짜 및 시간을 가져옵니다.
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH); // 월은 0부터 시작하므로 +1 해야 실제 월을 반영합니다.
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        yearPicker = view.findViewById(R.id.year_picker);
        monthPicker = view.findViewById(R.id.month_picker);
        dayPicker = view.findViewById(R.id.day_picker);
        hourPicker = view.findViewById(R.id.hour_picker);
        minutePicker = view.findViewById(R.id.minute_picker);

        // 년도 설정
        yearPicker.setMinValue(currentYear - 20);
        yearPicker.setMaxValue(currentYear + 20);
        yearPicker.setValue(currentYear);

        // 월 설정
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(currentMonth + 1); // Calendar.MONTH는 0부터 시작하므로 표시할 때는 +1 해야 합니다.

        // 일 설정
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        dayPicker.setValue(currentDay);

        // 시간 설정
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
        hourPicker.setValue(currentHour);

        // 분 설정
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(currentMinute);
    }

    private void confirmDateTime() {
        // 날짜와 시간을 문자열로 결합하여 전달
        String dateTime = yearPicker.getValue() + "년  " + monthPicker.getValue() + "월  " + dayPicker.getValue() +
                "일\n" + hourPicker.getValue() + "시  " + minutePicker.getValue() + "분";
        ((AddSchedule_WhenFragment) getTargetFragment()).onDateTimeSet(dateTime);
        dismiss();
    }
}

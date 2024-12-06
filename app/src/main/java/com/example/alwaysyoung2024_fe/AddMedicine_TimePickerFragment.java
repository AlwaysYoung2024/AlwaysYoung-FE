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

public class AddMedicine_TimePickerFragment extends DialogFragment {

    private NumberPicker dayPicker;
    private NumberPicker frequencyPicker;
    private NumberPicker timePicker;
    private Button confirmButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true); // 다이얼로그 바깥 터치시 종료 설정

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_medicine_time_picker, null);

        // 날짜 선택 (1~31)
        dayPicker = view.findViewById(R.id.day_picker);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);

        // 빈도 선택 (일에 한 번, 주에 한 번, 월에 한 번)
        frequencyPicker = view.findViewById(R.id.frequency_picker);
        frequencyPicker.setMinValue(0);
        frequencyPicker.setMaxValue(2);
        frequencyPicker.setDisplayedValues(new String[]{"일에 한 번", "주에 한 번", "개월에 한 번"});

        // 시간 선택 (00시~24시)
        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setMinValue(0);
        timePicker.setMaxValue(24);

        // 확인 버튼 설정
        confirmButton = view.findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            int day = dayPicker.getValue();
            String frequency = frequencyPicker.getDisplayedValues()[frequencyPicker.getValue()];
            int time = timePicker.getValue();

            // 전달할 데이터를 구성
            String selectedValues = "\n" + time + "시, " + day + frequency;

            if (getTargetFragment() instanceof AddMedicine_FrequencyFragment) {
                ((AddMedicine_FrequencyFragment) getTargetFragment()).setFrequency(selectedValues);
            }

            dismiss(); // 다이얼로그 종료
        });

        // 다이얼로그 취소 시 동작 설정
        dialog.setOnCancelListener(dialog1 -> {
            if (getTargetFragment() instanceof AddMedicine_FrequencyFragment) {
                ((AddMedicine_FrequencyFragment) getTargetFragment()).clearRadioGroupSelection();
            }
        });

        dialog.setContentView(view);
        return dialog;
    }
}

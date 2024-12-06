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

    private NumberPicker numberPicker;
    private Button confirmButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCanceledOnTouchOutside(true); // 다이얼로그 바깥 터치시 종료 설정

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_medicine_time_picker, null);

        numberPicker = view.findViewById(R.id.number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(3);
        numberPicker.setDisplayedValues(new String[]{"일에 한번", "주에 한번", "월에 한번"});

        confirmButton = view.findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            int value = numberPicker.getValue();
            String frequency = numberPicker.getDisplayedValues()[value - 1];
            if (getTargetFragment() instanceof AddMedicine_FrequencyFragment) {
                ((AddMedicine_FrequencyFragment) getTargetFragment()).setFrequency(frequency);
            }
            dismiss();
        });

        dialog.setOnCancelListener(dialog1 -> {
            if (getTargetFragment() instanceof AddMedicine_FrequencyFragment) {
                ((AddMedicine_FrequencyFragment) getTargetFragment()).clearRadioGroupSelection();
            }
        });

        dialog.setContentView(view);
        return dialog;
    }
}

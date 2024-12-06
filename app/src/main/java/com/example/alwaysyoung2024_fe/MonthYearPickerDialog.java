package com.example.alwaysyoung2024_fe;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.NumberPicker;

public class MonthYearPickerDialog extends Dialog {
    private final NumberPicker yearPicker;
    private final NumberPicker monthPicker;
    private final Button confirmButton;

    public interface OnDateSetListener {
        void onDateSet(int year, int month);
    }

    public MonthYearPickerDialog(Context context, int initialYear, int initialMonth, OnDateSetListener listener) {
        super(context);

        setContentView(R.layout.dialog_month_year_picker);

        yearPicker = findViewById(R.id.yearPicker);
        monthPicker = findViewById(R.id.monthPicker);
        confirmButton = findViewById(R.id.confirmButton);

        yearPicker.setWrapSelectorWheel(false); // 또는 true
        monthPicker.setWrapSelectorWheel(false); // 또는 true


        // 연도 설정
        yearPicker.setMinValue(2000);
        yearPicker.setMaxValue(2100);
        yearPicker.setValue(initialYear);

        // 월 설정
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(initialMonth);

        confirmButton.setOnClickListener(v -> {
            int selectedYear = yearPicker.getValue();
            int selectedMonth = monthPicker.getValue();
            listener.onDateSet(selectedYear, selectedMonth);
            dismiss();
        });
    }
}

package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddMedicine_FrequencyFragment extends Fragment {

    private CheckBox checkboxThrice, checkboxTwice, checkboxOther;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_medicine_frequency, container, false);

        // 체크박스 초기화
        checkboxThrice = view.findViewById(R.id.checkboxThrice);
        checkboxTwice = view.findViewById(R.id.checkboxTwice);
        checkboxOther = view.findViewById(R.id.checkboxOther);

        setupCheckboxListeners();

        return view;
    }

    private void setupCheckboxListeners() {
        checkboxThrice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxTwice.setChecked(false);
                checkboxOther.setChecked(false);
            }
        });

        checkboxTwice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxThrice.setChecked(false);
                checkboxOther.setChecked(false);
            }
        });

        checkboxOther.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxThrice.setChecked(false);
                checkboxTwice.setChecked(false);
                showTimePickerDialog(); // Show dialog if 'Other' is checked
            }
        });
    }

    private void showTimePickerDialog() {
        AddMedicine_TimePickerFragment timePicker = new AddMedicine_TimePickerFragment();
        timePicker.setTargetFragment(this, 0);
        timePicker.show(requireActivity().getSupportFragmentManager(), "AddMedicine_TimePickerFragment");
    }

    public void setFrequency(String frequency) {
        if (checkboxOther.isChecked()) {
            checkboxOther.setText("기타 (" + frequency + ")");
        }
    }

    public void validateAndProceed() {
        // '기타' 체크박스가 선택되었을 경우 TimingFragment를 건너뛰고 바로 SuccessActivity로 이동
        if (checkboxThrice.isChecked() || checkboxTwice.isChecked()) {
            // 빈도 선택이 완료된 경우 타이밍 화면으로 이동
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddMedicine_TimingFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (checkboxOther.isChecked()) {
            // '기타'를 선택하면 바로 AddSuccessActivity로 이동
            navigateToAddSuccessActivity();
        } else {
            // 복용 빈도가 선택되지 않은 경우
            Toast.makeText(requireContext(), "복용 빈도를 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToAddSuccessActivity() {
        // AddSuccessActivity로 이동
        Intent intent = new Intent(requireActivity(), AddSuccessActivity.class);
        startActivity(intent);
        requireActivity().finish(); // AddMedicineActivity 종료
    }


    private boolean validateFrequencySelection() {
        return checkboxThrice.isChecked() || checkboxTwice.isChecked() || checkboxOther.isChecked();
    }

    public void clearRadioGroupSelection() {
        // Clear all checkbox selections
        checkboxThrice.setChecked(false);
        checkboxTwice.setChecked(false);
        checkboxOther.setChecked(false);
    }
}

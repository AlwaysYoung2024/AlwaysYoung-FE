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
    private String selectedFrequency = ""; // 타임피커를 통해 설정된 복용 빈도를 저장할 변수

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
            } else {
                selectedFrequency = ""; // '기타' 체크 해제 시 선택된 빈도 초기화
            }
        });
    }

    private void showTimePickerDialog() {
        AddMedicine_TimePickerFragment timePicker = new AddMedicine_TimePickerFragment();
        timePicker.setTargetFragment(this, 0);
        timePicker.show(requireActivity().getSupportFragmentManager(), "AddMedicine_TimePickerFragment");
    }

    public void setFrequency(String frequency) {
        selectedFrequency = frequency; // 타임피커로부터 받은 빈도 저장
        if (checkboxOther.isChecked()) {
            checkboxOther.setText("기타 " + frequency + "");
        }
    }

    public void validateAndProceed() {
        // '기타' 체크박스가 선택되었을 경우
        if (checkboxOther.isChecked()) {
            // '기타' 옵션을 선택하고 타임피커로부터 데이터가 제대로 설정되었는지 확인
            if (!selectedFrequency.isEmpty()) {
                // 제대로 설정되었다면 바로 AddSuccessActivity로 이동
                navigateToAddSuccessActivity();
            } else {
                // 설정되지 않았다면 사용자에게 알림
                Toast.makeText(requireContext(), "복용 빈도를 설정해주세요.", Toast.LENGTH_SHORT).show();
            }
        } else if (checkboxThrice.isChecked() || checkboxTwice.isChecked()) {
            // 일반적인 빈도 선택이 완료된 경우 타이밍 화면으로 이동
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddMedicine_TimingFragment())
                    .addToBackStack(null)
                    .commit();
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

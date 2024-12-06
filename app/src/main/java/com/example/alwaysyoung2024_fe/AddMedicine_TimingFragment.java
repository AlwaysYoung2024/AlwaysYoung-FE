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

public class AddMedicine_TimingFragment extends Fragment {

    private CheckBox checkboxBefore, checkboxAfter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_medicine_timing, container, false);

        // 체크박스 초기화
        checkboxBefore = view.findViewById(R.id.checkboxBefore);
        checkboxAfter = view.findViewById(R.id.checkboxAfter);

        // 체크박스 리스너 설정
        setupCheckboxListeners();

        return view;
    }

    private void setupCheckboxListeners() {
        checkboxBefore.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxAfter.setChecked(false); // 다른 체크박스 해제
            }
        });

        checkboxAfter.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxBefore.setChecked(false); // 다른 체크박스 해제
            }
        });
    }

    public void validateAndProceed() {
        if (checkboxBefore.isChecked() || checkboxAfter.isChecked()) {
            completeProcess();
        } else {
            Toast.makeText(requireContext(), "복용 시간을 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void completeProcess() {
        // AddSuccessActivity로 이동
        Intent intent = new Intent(requireActivity(), AddSuccessActivity.class);
        startActivity(intent);
        requireActivity().finish(); // AddMedicineActivity 종료
    }
}

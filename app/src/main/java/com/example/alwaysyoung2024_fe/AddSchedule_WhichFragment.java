package com.example.alwaysyoung2024_fe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddSchedule_WhichFragment extends Fragment {

    private CheckBox checkboxGeneral, checkboxSpecial;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_schedule_which, container, false);

        // 체크박스 초기화
        checkboxGeneral = view.findViewById(R.id.checkboxGeneral);
        checkboxSpecial = view.findViewById(R.id.checkboxSpecial);

        setupCheckboxListeners();

        return view;
    }

    private void setupCheckboxListeners() {
        checkboxGeneral.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxSpecial.setChecked(false);
            }
        });

        checkboxSpecial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkboxGeneral.setChecked(false);
            }
        });
    }

    public void validateAndProceed() {
        if (checkboxGeneral.isChecked() || checkboxSpecial.isChecked()) {
            // AddSchedule_WhenFragment로 전환
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddSchedule_WhenFragment())
                    .addToBackStack(null)
                    .commit();
        } else {
            Toast.makeText(requireContext(), "진료 항목을 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }
}

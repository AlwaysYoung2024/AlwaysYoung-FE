package com.example.alwaysyoung2024_fe;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddSchedule_WhereFragment extends Fragment {

    private EditText hospitalNameInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_schedule_where, container, false);

        // EditText 초기화
        hospitalNameInput = view.findViewById(R.id.hospitalNameInput);

        return view;
    }

    public void validateAndProceed() {
        String hospitalName = hospitalNameInput.getText().toString().trim();
        if (TextUtils.isEmpty(hospitalName)) {
            Toast.makeText(requireContext(), "병원 이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // AddSchedule_WhichFragment로 전환
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddSchedule_WhichFragment())
                .addToBackStack(null)
                .commit();
    }
}

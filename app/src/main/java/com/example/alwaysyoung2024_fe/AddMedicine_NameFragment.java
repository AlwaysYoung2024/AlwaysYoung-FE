package com.example.alwaysyoung2024_fe;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddMedicine_NameFragment extends Fragment {

    private EditText medicineNameInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // fragment_add_medicine_name 레이아웃을 인플레이트
        View view = inflater.inflate(R.layout.fragment_add_medicine_name, container, false);

        // EditText 초기화
        medicineNameInput = view.findViewById(R.id.medicineNameInput);

        // 입력 이벤트 감지
        medicineNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력 전 상태
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isEnglishInput(s.toString())) {
                    // 영어 입력 시 오류 메시지 표시
                    medicineNameInput.setError("한글 키보드로 전환하세요."); // 입력창 아래 메시지 표시
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력 후 상태
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 키보드 자동 표시
        showKeyboard();
    }

    private void showKeyboard() {
        if (medicineNameInput != null) {
            medicineNameInput.requestFocus(); // EditText에 포커스를 강제로 설정
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(medicineNameInput, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    // 입력값이 영어인지 확인하는 메서드
    private boolean isEnglishInput(String text) {
        return text.matches("^[a-zA-Z\\s]+$");
    }

    public void validateAndProceed() {
        String input = medicineNameInput.getText().toString().trim();
        if (input.isEmpty()) {
            // 입력값이 비어있을 경우 오류 메시지 표시
            medicineNameInput.setError("약 이름을 입력하세요.");
            return;
        }

        if (isEnglishInput(input)) {
            // 영어 입력일 경우 오류 메시지 표시
            medicineNameInput.setError("한글 키보드로 전환하세요.");
            return;
        }

        // AddMedicine_Frequency 프래그먼트로 전환
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddMedicine_FrequencyFragment())
                .addToBackStack(null) // 뒤로가기 가능하도록 스택 추가
                .commit();
    }
}

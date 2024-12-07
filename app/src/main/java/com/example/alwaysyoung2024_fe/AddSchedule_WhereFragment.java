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

public class AddSchedule_WhereFragment extends Fragment {

    private EditText hospitalNameInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // fragment_add_schedule_where 레이아웃을 인플레이트
        View view = inflater.inflate(R.layout.fragment_add_schedule_where, container, false);

        // EditText 초기화
        hospitalNameInput = view.findViewById(R.id.hospitalNameInput);

        // 입력 이벤트 감지
        hospitalNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력 전 상태
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isEnglishInput(s.toString())) {
                    // 영어 입력 시 오류 메시지 표시
                    hospitalNameInput.setError("한글 키보드로 전환하세요."); // 입력창 아래 메시지 표시
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
        if (hospitalNameInput != null) {
            hospitalNameInput.requestFocus(); // EditText에 포커스를 강제로 설정
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(hospitalNameInput, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    // 입력값이 영어인지 확인하는 메서드
    private boolean isEnglishInput(String text) {
        return text.matches("^[a-zA-Z\\s]+$");
    }

    public void validateAndProceed() {
        String input = hospitalNameInput.getText().toString().trim();
        if (input.isEmpty()) {
            // 입력값이 비어있을 경우 오류 메시지 표시
            hospitalNameInput.setError("병원 이름을 입력하세요.");
            return;
        }

        if (isEnglishInput(input)) {
            // 영어 입력일 경우 오류 메시지 표시
            hospitalNameInput.setError("한글 키보드로 전환하세요.");
            return;
        }

        // AddSchedule_WhichFragment로 전환
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddSchedule_WhichFragment())
                .addToBackStack(null) // 뒤로가기 가능하도록 스택 추가
                .commit();
    }
}

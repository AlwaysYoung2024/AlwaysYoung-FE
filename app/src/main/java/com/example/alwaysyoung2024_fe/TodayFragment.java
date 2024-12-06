package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 간단한 {@link Fragment} 서브클래스입니다.
 * {@link TodayFragment#newInstance} 팩토리 메서드를 사용하여
 * 이 프래그먼트의 인스턴스를 생성하세요.
 */
public class TodayFragment extends Fragment {

    // 프래그먼트 초기화 파라미터 키
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 파라미터
    private String mParam1;
    private String mParam2;

    public TodayFragment() {
        // 필수 빈 생성자
    }

    /**
     * 파라미터를 사용하여 새로운 프래그먼트 인스턴스를 생성하는 팩토리 메서드.
     *
     * @param param1 파라미터 1.
     * @param param2 파라미터 2.
     * @return 새롭게 생성된 TodayFragment 인스턴스.
     */
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 레이아웃을 인플레이트
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        // 일정 추가 버튼 설정
        Button buttonAddSchedule = view.findViewById(R.id.button_add_schedule);
        buttonAddSchedule.setOnClickListener(v -> {
            // AddScheduleActivity로 이동
            Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
            startActivity(intent);
        });

        // 약 추가 버튼 설정
        Button buttonAddMedicine = view.findViewById(R.id.button_add_medicine);
        buttonAddMedicine.setOnClickListener(v -> {
            // AddMedicineActivity로 이동
            Intent intent = new Intent(getActivity(), AddMedicineActivity.class);
            startActivity(intent);
        });

        return view;
    }
}

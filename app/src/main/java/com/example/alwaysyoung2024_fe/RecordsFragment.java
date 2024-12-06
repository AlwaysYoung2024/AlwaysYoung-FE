package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RecordsFragment extends Fragment {

    private TextView monthTextView;
    private Calendar calendar;
    private GridView calendarGridView;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public RecordsFragment() {
        // Required empty public constructor
    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RecordsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RecordsFragment newInstance(String param1, String param2) {
//        RecordsFragment fragment = new RecordsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_records,container,false);

        monthTextView = view.findViewById(R.id.month);
        calendarGridView = view.findViewById(R.id.calendarGridView);
        ImageView backArrow = view.findViewById(R.id.back_arrow);
        ImageView forwardArrow = view.findViewById(R.id.forward_arrow);

        calendar = Calendar.getInstance();

        updateMonth();
        loadRecordsForMonth();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,-1);
                updateMonth();
                loadRecordsForMonth();
            }
        });

        forwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                updateMonth();
                loadRecordsForMonth();
            }
        });

        MaterialButton hospital_schedule_btn = view.findViewById(R.id.hospital_schedule_button);
        hospital_schedule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HospitalScheduleActivity.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void updateMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월", Locale.KOREAN);
        monthTextView.setText(sdf.format(calendar.getTime()));
    }

    private void loadRecordsForMonth(){
        AppDataManager appDataManager = new AppDataManager(requireContext());
        Calendar startDate = appDataManager.getStartDateAsCalendar();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        //날짜 리스트 생성
        List<String> dates = new ArrayList<>();
        List<Integer> stateColors = new ArrayList<>();

        //현재 달의 첫날 설정
        calendar.set(Calendar.DAY_OF_MONTH,1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //빈 칸 채우기 (이전 달의 남은 칸)
        for(int i=0; i < firstDayOfWeek;i++){
            dates.add("");
            stateColors.add(0);
        }

        //현재 달 날짜 채우기
        for(int day = 1; day <= daysInMonth; day++){
            dates.add(String.valueOf(day));

            int statePercentage = (day * 10) % 100;
            stateColors.add(statePercentage);
        }

        CalendarAdapter adapter = new CalendarAdapter(requireContext(),dates, stateColors, startDate);
        calendarGridView.setAdapter(adapter);
    }
}
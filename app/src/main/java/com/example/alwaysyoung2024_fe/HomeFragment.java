package com.example.alwaysyoung2024_fe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        TextView dateText = view.findViewById(R.id.dateText);

        String currentDate = getCurrentDate();
        dateText.setText(currentDate);
        // Inflate the layout for this fragment
        return view;
    }

    private String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,MMMM dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
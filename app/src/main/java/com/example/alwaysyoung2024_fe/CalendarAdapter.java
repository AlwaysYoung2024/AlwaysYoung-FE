package com.example.alwaysyoung2024_fe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> dates;
    private final List<Integer> stateColors;
    private final Calendar startDate;
    private final Calendar today;// 상태를 나타내는 색상 리스트

    public CalendarAdapter(Context context, List<String> dates, List<Integer> stateColors,Calendar startDate) {
        this.context = context;
        this.dates = dates;
        this.stateColors = stateColors;
        this.startDate = startDate;
        this.today = Calendar.getInstance();
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_day_item, parent, false);
        }

        TextView dayText = convertView.findViewById(R.id.tvDay);
        View circleBackground = convertView.findViewById(R.id.circleBackground);


        // 날짜 표시

        dayText.setText(dates.get(position));

        // 상태에 따라 배경색 변경
        if(!dates.get(position).isEmpty()){
            int day = Integer.parseInt(dates.get(position));
            Calendar currentDay = (Calendar) startDate.clone();
            currentDay.set(Calendar.DAY_OF_MONTH,day);

            if(currentDay.after(today)){
                circleBackground.setVisibility(View.GONE); // 미래 날짜는 동그라미 표시 안 함
            } else if (currentDay.before(startDate)) {
                circleBackground.setVisibility(View.GONE); // 가입 이전 날짜는 동그라미 표시 안 함
            } else{
                circleBackground.setVisibility(View.VISIBLE); // 현재 날짜 범위 내에서만 동그라미 표시

                int statePercentage = stateColors.get(position);
                int alpha = 50 + (statePercentage * 205 / 100);
                GradientDrawable background = (GradientDrawable) circleBackground.getBackground();
                background.setColor(Color.argb(alpha,0,128,0));
            }
        } else {
            circleBackground.setVisibility(View.GONE); // 빈 날짜 칸 처리
        }

        return convertView;
    }
}
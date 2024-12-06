package com.example.alwaysyoung2024_fe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        // 기본값으로 초기화
        circleBackground.setVisibility(View.GONE);
        dayText.setText("");

        if (!dates.get(position).isEmpty()) {
            dayText.setText(dates.get(position));

            if (stateColors.get(position) > 0) {
                // 동그라미 표시
                circleBackground.setVisibility(View.VISIBLE);

                int statePercentage = stateColors.get(position);
                int alpha = 50 + (statePercentage * 205 / 100);
                GradientDrawable background = (GradientDrawable) circleBackground.getBackground();
                background.setColor(Color.argb(alpha, 0, 128, 0));

                circleBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String clickedDate = dates.get(position); // 클릭된 날짜 가져오기
                        Calendar selectedDate = (Calendar) startDate.clone();
                        selectedDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(clickedDate));

                        Intent intent = new Intent(context, DayRecordActivity.class);
                        intent.putExtra("selectedDate",String.format(Locale.KOREAN,"%02d월 %02d일",
                                selectedDate.get(Calendar.MONTH)+1,
                                selectedDate.get(Calendar.DAY_OF_MONTH)));
                        context.startActivity(intent);
                    }
                });
            }
        }

        return convertView;
    }
}
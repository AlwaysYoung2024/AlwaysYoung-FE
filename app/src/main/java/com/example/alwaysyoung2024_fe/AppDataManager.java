package com.example.alwaysyoung2024_fe;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppDataManager {
    private static final String PREFS_NAME = "AppDataPrefs";
    private static final String START_DATE_KEY = "startDate";
    public AppDataManager instance;
    private final SharedPreferences prefs;

    public AppDataManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public int getCurrentMealStep() {
        return prefs.getInt("currentMealStep", 0);
    }

    public int getCurrentWaterStep() {
        return prefs.getInt("currentWaterStep", 0);
    }

    public int getCurrentMedicineStep() {
        return prefs.getInt("currentMedicineStep", 0);
    }

    public void saveStartDateIfNeeded() {
        if (!prefs.contains(START_DATE_KEY)) {
            // 앱 시작 날짜 저장
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
            prefs.edit().putString(START_DATE_KEY, sdf.format(calendar.getTime())).apply();
        }
    }

    public String getStartDate() {
        return prefs.getString(START_DATE_KEY, null);
    }

    public Calendar getStartDateAsCalendar() {
        String startDate = getStartDate();
        if (startDate == null) return null;

        Calendar calendar = Calendar.getInstance();
        String[] dateParts = startDate.split("-");
        calendar.set(Calendar.YEAR, Integer.parseInt(dateParts[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateParts[1]) - 1); // 0-based month
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[2]));
        return calendar;
    }
}


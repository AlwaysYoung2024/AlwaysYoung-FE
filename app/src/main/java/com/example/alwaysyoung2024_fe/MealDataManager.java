package com.example.alwaysyoung2024_fe;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MealDataManager {
    private static final String PREFS_NAME = "MealPrefs";
    private static final String KEY_LAST_UPDATE_DATE = "last_update_date";

    private SharedPreferences sharedPreferences;

    public MealDataManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // 마지막 업데이트된 날짜 저장
    public void saveLastUpdateDate(String date) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAST_UPDATE_DATE, date);
        editor.apply();
    }

    // 마지막 업데이트된 날짜 가져오기
    public String getLastUpdateDate() {
        return sharedPreferences.getString(KEY_LAST_UPDATE_DATE, ""); // 기본값: 없음
    }

    // 오늘 날짜 반환
    public String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    // 날짜가 새로운 날인지 확인
    public boolean isNewDay() {
        String today = getTodayDate();
        String lastUpdateDate = getLastUpdateDate();
        return !today.equals(lastUpdateDate); // 날짜가 다르면 새로운 날
    }
}

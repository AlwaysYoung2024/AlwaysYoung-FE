package com.example.alwaysyoung2024_fe;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MedicineDataManager {
    private static final String PREFS_NAME = "MedicinePrefs";
    private static final String KEY_LAST_MEDICINE_DATE = "last_medicine_date";
    private static final String KEY_CURRENT_MEDICINE = "current_medicine";
    private static final String KEY_TOTAL_MEDICINE_COUNT = "total_medicine_count";


    private SharedPreferences sharedPreferences;

    // Constructor
    public MedicineDataManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // 현재 복용 중인 약 저장
    public void saveCurrentMedicine(String medicine) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CURRENT_MEDICINE, medicine);
        editor.apply();
    }

    // 현재 복용 중인 약 가져오기
    public String getCurrentMedicine() {
        return sharedPreferences.getString(KEY_CURRENT_MEDICINE, "08:00 위장약"); // 기본값
    }

    // 마지막 복용 날짜 저장
    public void saveLastMedicineDate(String date) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAST_MEDICINE_DATE, date);
        editor.apply();
    }

    // 마지막 복용 날짜 가져오기
    public String getLastMedicineDate() {
        return sharedPreferences.getString(KEY_LAST_MEDICINE_DATE, ""); // 기본값: 없음
    }

    // 오늘 날짜 반환
    public String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    // 새로운 날인지 확인
    public boolean isNewDay() {
        String today = getTodayDate();
        String lastDate = getLastMedicineDate();
        return !today.equals(lastDate); // 날짜가 다르면 새로운 날
    }

    // 총 복용해야 할 약의 개수 저장
    public void saveTotalMedicineCount(int count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_TOTAL_MEDICINE_COUNT, count);
        editor.apply();
    }

    // 총 복용해야 할 약의 개수 가져오기
    public int getTotalMedicineCount() {
        return sharedPreferences.getInt(KEY_TOTAL_MEDICINE_COUNT, 3); // 기본값: 3개
    }
}

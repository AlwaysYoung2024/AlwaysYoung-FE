package com.example.alwaysyoung2024_fe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alwaysyoung2024_fe.databinding.FragmentHomeBinding;
import com.example.alwaysyoung2024_fe.MealDataManager;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private MedicineDataManager medicineDataManager;
    private MealDataManager mealDataManager;

    private int currentTotalStep = 0;
    private int totalProgress;
    private String[] mealSteps = {"아침 밥 먹기","점심 밥 먹기","저녁 밥 먹기"};
    private int currentMedicineStep = 0;
    private int currentMealStep = 0;
    private int currentWaterStep = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        medicineDataManager = new MedicineDataManager(requireContext());
        mealDataManager = new MealDataManager(requireContext());

        if (medicineDataManager.isNewDay()) {
            medicineDataManager.saveLastMedicineDate(medicineDataManager.getTodayDate());
            currentTotalStep = 0; // 진행 상태 초기화
        }

        totalProgress = 8 + medicineDataManager.getTotalMedicineCount();

        Button emergencyBtn = binding.emergencyButton;
        //긴급호출 버튼 클릭시
        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //긴급호출 액티비티로 전환
                startActivity(new Intent(getActivity(), EmergencyActivity.class));
            }
        });

        //날짜 텍스트
        TextView dateText = view.findViewById(R.id.dateText);
        String currentDate = getCurrentDate();
        dateText.setText(currentDate);

        View totalStateFillBar = binding.currentStateFillBar;
        View totalStateBar = binding.currentStateBar;

        View mealStateFillBar = binding.mealStateFillBar;
        View mealStateBar = binding.mealStateBar;
        GradientDrawable background = (GradientDrawable) mealStateBar.getBackground();
        background.setColor(Color.parseColor("#FFFFFF"));

        View waterStateFillBar = binding.waterStateFillBar;
        View waterStateBar = binding.waterStateBar;


        MaterialButton meal_check_Btn = binding.mealCheckButton;
        meal_check_Btn.setText(mealSteps[currentMealStep]);

        if(mealDataManager.isNewDay()){
            currentMealStep = 0;
            meal_check_Btn.setText(mealSteps[currentMealStep]);
            meal_check_Btn.setEnabled(true);
            meal_check_Btn.setBackgroundTintList(getResources().getColorStateList(R.color.main_color));
            mealDataManager.saveLastUpdateDate(mealDataManager.getTodayDate());
        } else if (currentMealStep == mealSteps.length) {
            meal_check_Btn.setText("식사 완료");
            meal_check_Btn.setEnabled(false);
            meal_check_Btn.setBackgroundTintList(getResources().getColorStateList(R.color.home_button_clicked_color));

        }

        Button water_check_Btn = binding.waterCheckButton;

        //약
        MaterialButton medicine_check_Btn = binding.medicineCheckButton;
        TextView medicine_text = binding.medicineText;
        String currentMedicine = medicineDataManager.getCurrentMedicine();
        medicine_text.setText(currentMedicine);

        //약 먹기 버튼 클릭
        medicine_check_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMedicineDialog(medicine_check_Btn,totalStateFillBar,totalStateBar);
            }
        });
        //밥 먹기 버튼 클릭
        meal_check_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mealSteps[currentMealStep].equals("저녁 밥 먹기")){
                    showMealDialog(meal_check_Btn,totalStateFillBar,totalStateBar,mealStateFillBar,mealStateBar);
                } else {
                    showMealDialog(meal_check_Btn,totalStateFillBar,totalStateBar,mealStateFillBar,mealStateBar);
                }
            }
        });
        //물 먹기 버튼 클릭
        water_check_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaterDialog(water_check_Btn,totalStateFillBar,totalStateBar,waterStateFillBar,waterStateBar);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,MMMM dd일", Locale.KOREAN);
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void showMedicineDialog(Button medicine_check_Btn, View totalStateFillBar, View totalStateBar){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_medicine_check,null);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(846, 1000); // 원하는 크기로 설정 (px 단위)
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }});

        MaterialButton yesBtn = dialogView.findViewById(R.id.yes_button);
        MaterialButton noBtn = dialogView.findViewById(R.id.no_button);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTotalStep++;
                currentMedicineStep++;
                if(currentMedicineStep >= medicineDataManager.getTotalMedicineCount()){
                    currentMedicineStep = medicineDataManager.getTotalMedicineCount();
                    medicine_check_Btn.setText("복용 완료");
                    medicine_check_Btn.setEnabled(false);
                    medicine_check_Btn.setBackgroundTintList(getResources().getColorStateList(R.color.home_button_clicked_color));
                    medicine_check_Btn.setTextColor(Color.WHITE);
                    medicineDataManager.saveLastMedicineDate(medicineDataManager.getTodayDate());
                }
                updateStatusBar("total",currentTotalStep,totalStateFillBar,totalStateBar);
                dialog.dismiss();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showMealDialog(Button meal_check_Btn,View totalStateFillBar, View totalStateBar, View currentStateFillBar, View currentStateBar){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_meal_check,null);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(846, 1000); // 원하는 크기로 설정 (px 단위)
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }

            TextView dialogTextView = dialogView.findViewById(R.id.dialog_meal_message);
            dialogTextView.setText(getMealDialogText(currentMealStep));
        });

        MaterialButton yesBtn = dialogView.findViewById(R.id.yes_button);
        MaterialButton noBtn = dialogView.findViewById(R.id.no_button);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTotalStep++;
                currentMealStep++;
                if(currentMealStep >= mealSteps.length){
                    currentMealStep = mealSteps.length;
                    meal_check_Btn.setText("식사 완료");
                    meal_check_Btn.setEnabled(false);
                    meal_check_Btn.setBackgroundTintList(getResources().getColorStateList(R.color.home_button_clicked_color));
                    meal_check_Btn.setTextColor(Color.WHITE);
                    mealDataManager.saveLastUpdateDate(mealDataManager.getTodayDate());

                } else {
                    meal_check_Btn.setText(mealSteps[currentMealStep]);
                }
                updateStatusBar("total",currentTotalStep,totalStateFillBar,totalStateBar);
                updateStatusBar("meal",currentMealStep, currentStateFillBar, currentStateBar);
                dialog.dismiss();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private String getMealDialogText(int step){
        switch (step) {
            case 0:
                return "아침 밥을\n드셨나요?";
            case 1:
                return "점심 밥을\n드셨나요?";
            case 2:
                return "저녁 밥을\n드셨나요?";
            default:
                return "식사를\n하셨나요?";
        }
    }

    private void showWaterDialog(Button water_check_btn, View totalStateFillBar, View totalStateBar, View currentStateFillBar, View currentStateBar){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_water_check,null);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(846, 1000); // 원하는 크기로 설정 (px 단위)
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }});

        MaterialButton yesBtn = dialogView.findViewById(R.id.yes_button);
        MaterialButton noBtn = dialogView.findViewById(R.id.no_button);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTotalStep++;
                currentWaterStep++;
                if(currentWaterStep >= 5) {
                    currentWaterStep = 5;
                    water_check_btn.setText("섭취 완료");
                    water_check_btn.setEnabled(false);
                    water_check_btn.setBackgroundTintList(getResources().getColorStateList(R.color.home_button_clicked_color));
                    water_check_btn.setTextColor(Color.WHITE);
                    mealDataManager.saveLastUpdateDate(mealDataManager.getTodayDate());
                }

                updateStatusBar("total",currentTotalStep,totalStateFillBar,totalStateBar);
                updateStatusBar("water",currentWaterStep, currentStateFillBar, currentStateBar);
                dialog.dismiss();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void updateStatusBar(String type, int step, View fillBar, View fullBar){
        int fullWidth = fullBar.getWidth();

        float fillRatio;

        if(type.equals("meal"))
            fillRatio = step / 3.0f;
        else if (type.equals("water"))
            fillRatio = step / 5.0f;
        else
            fillRatio = step / (float) totalProgress;

        int newWidth = (int) (fullWidth * fillRatio);

        ViewGroup.LayoutParams params = fillBar.getLayoutParams();
        params.width = newWidth;
        fillBar.setLayoutParams(params);
    }
}
package com.example.alwaysyoung2024_fe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class OnboardingAdapter extends FragmentStateAdapter {

    private List<Integer> layoutList;

    public OnboardingAdapter(@NonNull FragmentActivity fragmentActivity, List<Integer> layoutList) {
        super(fragmentActivity);
        this.layoutList = layoutList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return OnboardingPageFragment.newInstance(layoutList.get(position));
    }

    @Override
    public int getItemCount() {
        return layoutList.size();
    }
}

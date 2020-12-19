package com.example.cinema.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinema.CardPhimFragment;

public class ViewPagerPhimAdapter extends FragmentStateAdapter {
    //Số lượng tab
    private static final int CARD_ITEM_SIZE = 2;

    public ViewPagerPhimAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CardPhimFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}

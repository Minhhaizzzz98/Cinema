package com.example.cinema.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinema.ui.CardDienAnhFragment;

public class DienAnhViewPagerAdapter extends FragmentStateAdapter {
    //Số lượng tab
    private static final int CARD_ITEM_SIZE = 3;

    public DienAnhViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CardDienAnhFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return CARD_ITEM_SIZE;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}

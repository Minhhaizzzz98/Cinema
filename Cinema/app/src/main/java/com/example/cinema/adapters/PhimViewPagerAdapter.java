package com.example.cinema.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cinema.ui.all;
import com.example.cinema.ui.dangchieu;
import com.example.cinema.ui.sapchieu;

public class PhimViewPagerAdapter extends FragmentPagerAdapter {
    public PhimViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new all();
        }
        else if (position == 1)
        {
            fragment = new dangchieu();
        }
        else
        {
            fragment=new sapchieu();
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Tất cả";
        }
        else if (position == 1)
        {
            title = "Đang chiếu";
        }
        else
        {
            title = "Sắp chiếu";
        }

        return title;

    }


    @Override
    public int getCount() {
        return 3;
    }
}

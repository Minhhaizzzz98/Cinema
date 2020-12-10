package com.example.cinema.adapters;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cinema.ui.all;
import com.example.cinema.ui.binhluan;
import com.example.cinema.ui.dangchieu;
import com.example.cinema.ui.nhanvat;
import com.example.cinema.ui.sapchieu;
import com.example.cinema.ui.tintuc;

public class DienAnhViewPagerAdapter extends FragmentPagerAdapter {
    public DienAnhViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0: fragment = new binhluan(); break;
            case 1: fragment = new tintuc(); break;
            case 2: fragment = new nhanvat(); break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Bình luận";
        }
        else if (position == 1)
        {
            title = "Tin tức";
        }
        else
        {
            title = "Nhân vật";
        }

        return title;

    }

    @Override
    public int getCount() {
        return 3;
    }
}

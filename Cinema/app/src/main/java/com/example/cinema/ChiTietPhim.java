package com.example.cinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.cinema.adapters.ViewPagerAdapter;
import com.example.cinema.adapters.ViewPagerChiTietAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChiTietPhim extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);
        tabLayout=(TabLayout)findViewById(R.id.tabChiTiet);
        viewPager=(ViewPager2)findViewById(R.id.vpChiTiet);
        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Lịch Chiếu");
                        break;
                    case 1:
                        tab.setText("Thông Tin");
                        break;
                    case 2:
                        tab.setText("Bình Luận");
                        break;
                }
            }
        }).attach();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 1:
                    {
                        ViewGroup.LayoutParams params = tabLayout.getLayoutParams();
                        params.height = 0;
                        tabLayout.setLayoutParams(params);
                        break;
                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private ViewPagerChiTietAdapter createCardAdapter() {
        ViewPagerChiTietAdapter adapter = new  ViewPagerChiTietAdapter(this);
        return adapter;
    }
}
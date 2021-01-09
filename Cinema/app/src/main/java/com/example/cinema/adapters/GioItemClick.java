package com.example.cinema.adapters;

import android.widget.RelativeLayout;

import com.example.cinema.models.GioChieu;
import com.example.cinema.models.Lich;

public interface GioItemClick {
    void onGioClick(GioChieu movie, RelativeLayout relativeLayout);
}

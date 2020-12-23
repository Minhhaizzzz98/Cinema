package com.example.cinema.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cinema.R;

public class CardChiTietFragment  extends Fragment {
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    View rootView;

    public CardChiTietFragment() {
    }
    public static CardChiTietFragment newInstance(Integer counter){
        CardChiTietFragment fragment= new CardChiTietFragment();
        Bundle args= new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            counter= getArguments().getInt(ARG_COUNT);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.chitiet_datve, container, false);
                break;
            case 1:
                rootView= inflater.inflate(R.layout.activity_chi_tiet_phim, container, false);
                break;
            case 2:
                rootView= inflater.inflate(R.layout.chitiet_tintuc, container, false);
                break;
        }
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}

package com.example.cinema.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.adapters.DienAnhViewPagerAdapter;
import com.example.cinema.adapters.PhimViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DienAnhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DienAnhFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    DienAnhViewPagerAdapter dienAnhViewPagerAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DienAnhFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DienAnhFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DienAnhFragment newInstance(String param1, String param2) {
        DienAnhFragment fragment = new DienAnhFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dien_anh, container, false);

        //khai báo id
        tabLayout = (TabLayout) view.findViewById(R.id.tab_dienanh);
        viewPager = (ViewPager) view.findViewById(R.id.vpg_dienanh);

        dienAnhViewPagerAdapter=new DienAnhViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(dienAnhViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
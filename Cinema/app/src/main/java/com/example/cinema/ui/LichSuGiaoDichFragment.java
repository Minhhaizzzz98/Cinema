package com.example.cinema.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.cinema.R;
import com.example.cinema.adapters.LichSuGiaoDichAdapter;
import com.example.cinema.models.GiaoDich;

import java.io.BufferedReader;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LichSuGiaoDichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LichSuGiaoDichFragment extends Fragment {

    ImageButton imageButton;
    RecyclerView recyclerViewGiaoDich;
    LinkedList<GiaoDich> lstGiaoDich= new LinkedList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LichSuGiaoDichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LichSuGiaoDichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LichSuGiaoDichFragment newInstance(String param1, String param2) {
        LichSuGiaoDichFragment fragment = new LichSuGiaoDichFragment();
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
        View view = inflater.inflate(R.layout.fragment_lich_su_giao_dich, container, false);
        imageButton= view.findViewById(R.id.imageButton);

        lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "1/1/2020", 45000));
        lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "2/1/2020", 45000));
        lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "3/1/2020", 45000));
        lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "4/1/2020", 45000));
        lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "5/1/2020", 45000));


        recyclerViewGiaoDich= view.findViewById(R.id.recyclerViewGiaoDich);
        LichSuGiaoDichAdapter adapter= new LichSuGiaoDichAdapter(lstGiaoDich, getActivity());
        recyclerViewGiaoDich.setAdapter(adapter);
        recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));

        registerForContextMenu(imageButton);
        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_lich_su_giao_dich, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getTitle()== "Tăng dần"){
            //do your code
        }
        else if(item.getTitle()== "Giảm dần"){

        }

        return super.onContextItemSelected(item);
    }
}
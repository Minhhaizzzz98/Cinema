package com.example.cinema.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.adapters.ActorAdapter;
import com.example.cinema.adapters.DienVien_DaoDienAdapter;
import com.example.cinema.models.Actor;

import java.util.LinkedList;

public class CardChiTietFragment  extends Fragment {
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    View rootView;

    RecyclerView rvDienVien, rvDaoDien;

    LinkedList<Actor> actorlist= new LinkedList<Actor>();
    LinkedList<Actor> directorlist= new LinkedList<Actor>();

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
                rootView= inflater.inflate(R.layout.chi_tiet_thongtin, container, false);

                rvDienVien= rootView.findViewById(R.id.rvDienVien);
                rvDaoDien= rootView.findViewById(R.id.rvDaoDien);

                actorlist.add(new Actor(R.drawable.dienvien1, "Minh Hai"));
                actorlist.add(new Actor(R.drawable.dienvien1, "Minh Hai"));
                actorlist.add(new Actor(R.drawable.dienvien1, "Minh Hai"));

                directorlist.add(new Actor(R.drawable.dienvien1, "Minh Hai"));
                directorlist.add(new Actor(R.drawable.dienvien1, "Minh Hai"));
                directorlist.add(new Actor(R.drawable.dienvien1, "Minh Hai"));

                DienVien_DaoDienAdapter actorAdapter = new DienVien_DaoDienAdapter(getContext(), actorlist);
                rvDienVien.setAdapter(actorAdapter);
                rvDienVien.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                DienVien_DaoDienAdapter directorAdapter = new DienVien_DaoDienAdapter(getContext(), actorlist);
                rvDaoDien.setAdapter(directorAdapter);
                rvDaoDien.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

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

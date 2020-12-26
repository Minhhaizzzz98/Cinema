package com.example.cinema.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.adapters.ActorAdapter;
import com.example.cinema.adapters.DienVien_DaoDienAdapter;
import com.example.cinema.adapters.GioChieuAdapter;
import com.example.cinema.adapters.LichChieuAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.models.Actor;
import com.example.cinema.models.Lich;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardChiTietFragment  extends Fragment {
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    View rootView;
    private MovieItemClickListener movieItemClickListener;
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
                Spinner spinner = (Spinner)rootView.findViewById(R.id.diadiem_spinner);
                Spinner spinnerRap=(Spinner)rootView.findViewById(R.id.rap_spinner);
                RecyclerView rvGio=(RecyclerView)rootView.findViewById(R.id.rv_giochieu);
                // Create an ArrayAdapter using the string array and a default spinner layout
                loadSpinDiaDiem(spinner,R.array.diadiem_array);
                loadSpinDiaDiem(spinnerRap,R.array.rap_array);
                RecyclerView rvLich=(RecyclerView)rootView.findViewById(R.id.rv_lich);
                LichChieuAdapter lichChieuAdapter=new LichChieuAdapter(getContext(),createLich(),movieItemClickListener);
                rvLich.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvLich.setAdapter(lichChieuAdapter);
                createAdapterGio(rvGio,createGio());
                break;
            case 1:
                rootView= inflater.inflate(R.layout.chi_tiet_thongtin, container, false);

                rvDienVien= rootView.findViewById(R.id.rvDienVien);
                rvDaoDien= rootView.findViewById(R.id.rvDaoDien);

                actorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                actorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                actorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));

                directorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                directorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                directorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));

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
    public void loadSpinDiaDiem(Spinner spin, int n)
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                n, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spin.setAdapter(adapter);
    }
    public List<Lich> createLich()
    {
        List<Lich> lstLich=new ArrayList<>();
        lstLich.add(new Lich("Chủ Nhật","20/12"));
        lstLich.add(new Lich("Thứ 2","20/12"));
        lstLich.add(new Lich("Thứ 3","20/12"));
        lstLich.add(new Lich("Thứ 4","20/12"));
        lstLich.add(new Lich("Thứ 5","20/12"));
        lstLich.add(new Lich("Thứ 6","20/12"));
        return  lstLich;
    }
    private List<String> createGio()
    {
        List<String> lstGio=new ArrayList<>();
        lstGio.add("20:00");
        lstGio.add("18:00");
        lstGio.add("16:00");
        lstGio.add("15:00");
        lstGio.add("14:00");
        lstGio.add("10:00");
        lstGio.add("08:00");
        lstGio.add("06:00");
        lstGio.add("04:00");
        return  lstGio;
    }
    private  void createAdapterGio(RecyclerView rv,List<String> lst)
    {
        GioChieuAdapter gioChieuAdapter=new GioChieuAdapter(lst,getContext());
        rv.setLayoutManager(new GridLayoutManager(getContext(),4));
        rv.setAdapter(gioChieuAdapter);
    }
}

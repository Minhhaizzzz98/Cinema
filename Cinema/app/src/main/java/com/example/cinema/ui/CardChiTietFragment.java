package com.example.cinema.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.R;
import com.example.cinema.adapters.ActorAdapter;
import com.example.cinema.adapters.DienVien_DaoDienAdapter;
import com.example.cinema.adapters.GioChieuAdapter;
import com.example.cinema.adapters.LichChieuAdapter;
import com.example.cinema.adapters.LichItemClick;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.adapters.RapAdapter;
import com.example.cinema.adapters.SpinnerRapAdapter;
import com.example.cinema.models.Actor;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.DatabaseHandler;
import com.example.cinema.models.Lich;
import com.example.cinema.models.Rap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class CardChiTietFragment  extends Fragment {
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    View rootView;
    private MovieItemClickListener movieItemClickListener;
    private LichItemClick lichItemClick;
    RecyclerView rvDienVien, rvDaoDien;
    LinkedList<Rap> lstRap;
    LinkedList<Actor> actorlist= new LinkedList<Actor>();
    LinkedList<Actor> directorlist= new LinkedList<Actor>();
    //Khoi tạo textView Rap;
    TextView txtRap;
    Button btnDat;
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
        lstRap=new LinkedList<>();
        //createListRap();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.chitiet_datve, container, false);
                //Text view show khi click
                TextView txtNgayThang=rootView.findViewById(R.id.txtNgayChieu);
                txtRap=rootView.findViewById(R.id.txtRap);
                btnDat=rootView.findViewById(R.id.btnDatNgay);
                SpinnerRapAdapter spinnerRapAdapter=new SpinnerRapAdapter(getContext(),android.R.layout.simple_spinner_item,lstRap);
                Spinner spinner = (Spinner)rootView.findViewById(R.id.diadiem_spinner);
                Spinner spinnerRap=(Spinner)rootView.findViewById(R.id.rap_spinner);
                RecyclerView rvGio=(RecyclerView)rootView.findViewById(R.id.rv_giochieu);

                // Create an ArrayAdapter using the string array and a default spinner layout
                loadSpinDiaDiem(spinner,R.array.diadiem_array);
                //loadSpinDiaDiem(spinnerRap,R.array.rap_array);
                //spinnerRap.setAdapter(spinnerRapAdapter);
                RecyclerView rvLich=(RecyclerView)rootView.findViewById(R.id.rv_lich);
                LichChieuAdapter lichChieuAdapter=new LichChieuAdapter(getContext(),createLich(),lichItemClick=new LichItemClick() {
                    @Override
                    public void onLichClick(Lich movie, RelativeLayout relativeLayout) {
                        Toast.makeText(getContext(), movie.getThu()+"", Toast.LENGTH_SHORT).show();
                        String thu=movie.getThu();
                        String[] ngay=movie.getNgay().split("/");
                        thu=thu+","+"ngày "+ngay[0]+" tháng "+ngay[1]+" Năm " + Calendar.getInstance().get(Calendar.YEAR);
                        txtNgayThang.setText(thu);
                    }
                });
                rvLich.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvLich.setAdapter(lichChieuAdapter);
                createAdapterGio(rvGio,createGio());
                viewdata(spinnerRapAdapter,spinnerRap);
                btnDat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplication(),ChonGheActivity.class);
                       // DatabaseHandler databaseHandler=new DatabaseHandler(getContext());
                        //int id_ve=(int) databaseHandler.addVeValue(1,0,1,0,0);
                       // intent.putExtra("message",id_ve);
                        startActivity(intent);



                    }
                });
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
    private void createListRap()
    {
        lstRap=new LinkedList<>();
        lstRap.add(new Rap(1,"Cinema Tân Bình 1"));
        lstRap.add(new Rap(2,"Cinema Tân Bình 2"));
        lstRap.add(new Rap(3,"Cinema Tân Bình 3"));
        lstRap.add(new Rap(4,"Cinema Tân Bình 4"));
        lstRap.add(new Rap(5,"Cinema Tân Bình 5"));

    }
    public void viewdata(SpinnerRapAdapter adapter,Spinner spinner) {
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/Rap";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        Rap rap= new Rap();
                        rap.setId(jsonObject.getInt("id"));
                        rap.setName(jsonObject.getString("TenRap"));

                        lstRap.add(rap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        // Here you get the current item (a User object) that is selected by its position
                        Rap user = adapter.getItem(position);
                        // Here you can do the action you want to...
                        Toast.makeText(getContext(), "ID: " + user.getId() + "\nName: " + user.getName(),
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapter) {  }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}

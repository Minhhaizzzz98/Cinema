package com.example.cinema.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.R;
import com.example.cinema.adapters.LichSuGiaoDichAdapter;
import com.example.cinema.adapters.SpinnerQuanAdapter;
import com.example.cinema.adapters.SpinnerRapAdapter;
import com.example.cinema.adapters.ThongKeGiaoDichAdapter;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.GiaoDich;
import com.example.cinema.models.KhachHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.KeyAgreement;


public class CardFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    KhachHang khachHang= new KhachHang();
    LinkedList<String> arrTinh = new LinkedList<>();
    ImageButton imageButton;
    RecyclerView recyclerViewGiaoDich;
    LinkedList<GiaoDich> lstGiaoDich= new LinkedList<>();
    LinkedList<GiaoDich> lstGiaoDichTangDan= new LinkedList<>();
    LinkedList<GiaoDich> lstThongKeGD= new LinkedList<>();
    LinkedList<GiaoDich> lstThongKeGDTheoThang= new LinkedList<>();
    private static final String ARG_COUNT = "param1";
    private static Integer counter;

    View rootView;

    public CardFragment(){

    };
    public static CardFragment newInstance(Integer counter){
        CardFragment fragment= new CardFragment();
        Bundle args= new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }


    // gắn layout khác nhau vào nội dung của từng tab -> trả ra view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        khachHang.setHoTen(this.getArguments().getString("HOTEN"));
//        khachHang.setTenTK(this.getArguments().getString("TENTK"));
//        khachHang.setDiaChi(this.getArguments().getString("DIACHI"));
//        khachHang.setSDT(this.getArguments().getString("SDT"));
//        khachHang.setEmail(this.getArguments().getString("EMAIL"));
//        khachHang.setPassword(this.getArguments().getString("PASSWORD"));
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.layout_trang_ca_nhan, container, false);
                if((khachHang=layDataTuSharePre())!=null){
                    TextView tvTenDangNhap= rootView.findViewById(R.id.tvTenDangNhap);
                    TextView tvPhone= rootView.findViewById(R.id.tvPhone);
                    TextView tvEmail= rootView.findViewById(R.id.tvEmail);
//                    TextView tvTinh= rootView.findViewById(R.id.tvTinh);
                    TextView tvPassword= rootView.findViewById(R.id.tvPassword);
                    TextView tvNgaySinh= rootView.findViewById(R.id.tvNgaySinh);

                    tvTenDangNhap.setText(khachHang.getHoTen());
                    tvPhone.setText(khachHang.getSDT());
                    tvEmail.setText(khachHang.getEmail());
                    tvPassword.setText(khachHang.getPassword());
                    tvNgaySinh.setText(khachHang.getNgaySinh());

                    layDanhSachTinh();
                    Spinner spinnerTinh= rootView.findViewById(R.id.spinnerTinh);
                    SpinnerQuanAdapter adapter = new SpinnerQuanAdapter(getActivity(), R.layout.spinner_item, arrTinh);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter.notifyDataSetChanged();
                    spinnerTinh.setAdapter(adapter);

                    spinnerTinh.setOnItemSelectedListener(this);
                }

                break;
            case 1:
                rootView= inflater.inflate(R.layout.fragment_thong_ke_giao_dich, container, false);
                lstThongKeGD.add(new GiaoDich("2016", 100000));
                lstThongKeGD.add(new GiaoDich("2018", 100000));
                lstThongKeGD.add(new GiaoDich("2019", 100000));
                lstThongKeGD.add(new GiaoDich("2020", 100000));

                lstThongKeGDTheoThang.add(new GiaoDich("01/2016", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("03/2018", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("04/2018", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("08/2018", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("02/2019", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("03/2019", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("05/2020", 100000));
                lstThongKeGDTheoThang.add(new GiaoDich("09/2020", 100000));
                recyclerViewGiaoDich= rootView.findViewById(R.id.recyclerViewThongKeGD);
                ThongKeGiaoDichAdapter TKadapter= new ThongKeGiaoDichAdapter(lstThongKeGD, getActivity());
                recyclerViewGiaoDich.setAdapter(TKadapter);
                recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));

                ImageButton imgbtnLoc= rootView.findViewById(R.id.imgbtnLoc);
                imgbtnLoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                        popupMenu.inflate(R.menu.context_menu_thong_ke_giao_dich);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.theo_nam:
                                        ThongKeGiaoDichAdapter TKadapter= new ThongKeGiaoDichAdapter(lstThongKeGD, getActivity());
                                        recyclerViewGiaoDich.setAdapter(TKadapter);
                                        recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        return true;
                                    case R.id.theo_thang:
                                        TKadapter= new ThongKeGiaoDichAdapter(lstThongKeGDTheoThang, getActivity());
                                        recyclerViewGiaoDich.setAdapter(TKadapter);
                                        recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        return true;
                                    default:
                                        return false;
                                }

                            }
                        });
                        popupMenu.show(); }
                });
                break;
            case 2: rootView= inflater.inflate(R.layout.fragment_lich_su_giao_dich, container, false);
                ImageButton btn1=rootView.findViewById(R.id.imageButton);
                lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "1/1/2020", 45000));
                lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "2/1/2020", 45000));
                lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "3/1/2020", 45000));
                lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "4/1/2020", 45000));
                lstGiaoDich.add(new GiaoDich("Sắc đẹp khó cưỡng", "5/1/2020", 45000));

                lstGiaoDichTangDan.add(new GiaoDich("Sắc đẹp khó cưỡng", "5/1/2020", 45000));
                lstGiaoDichTangDan.add(new GiaoDich("Sắc đẹp khó cưỡng", "4/1/2020", 45000));
                lstGiaoDichTangDan.add(new GiaoDich("Sắc đẹp khó cưỡng", "3/1/2020", 45000));
                lstGiaoDichTangDan.add(new GiaoDich("Sắc đẹp khó cưỡng", "2/1/2020", 45000));
                lstGiaoDichTangDan.add(new GiaoDich("Sắc đẹp khó cưỡng", "1/1/2020", 45000));

                recyclerViewGiaoDich= rootView.findViewById(R.id.recyclerViewGiaoDich);
                LichSuGiaoDichAdapter adapter= new LichSuGiaoDichAdapter(lstGiaoDichTangDan, getActivity());
                recyclerViewGiaoDich.setAdapter(adapter);
                recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                        popupMenu.inflate(R.menu.context_menu_lich_su_giao_dich);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.tang_dan:
                                        LichSuGiaoDichAdapter adapter= new LichSuGiaoDichAdapter(lstGiaoDich, getActivity());
                                        recyclerViewGiaoDich.setAdapter(adapter);
                                        recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        return true;
                                    case R.id.giam_dan:
                                        adapter= new LichSuGiaoDichAdapter(lstGiaoDichTangDan, getActivity());
                                        recyclerViewGiaoDich.setAdapter(adapter);
                                        recyclerViewGiaoDich.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        return true;
                                    default:
                                        return false;
                                }

                            }
                        });
                        popupMenu.show(); }
                });
                break;
        }
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            counter= getArguments().getInt(ARG_COUNT);
        }

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public KhachHang layDataTuSharePre() {
        KhachHang kh= new KhachHang();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("isLoggedIn", false)){
            kh.setSDT(sharedPreferences.getString("phoneKey", null));
            kh.setPassword(sharedPreferences.getString("passwordKey", null));
            kh.setDiaChi(sharedPreferences.getString("address", null));
            kh.setEmail(sharedPreferences.getString("email", null));
            kh.setHoTen(sharedPreferences.getString("fullName", null));
            kh.setNgaySinh(sharedPreferences.getString("birthday", null));
        }
        return kh;
    }
    public void layDanhSachTinh() {
        String url= "http://"+ DataHelperConnnect.ipConnect+"/api/Tinh";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);

                        arrTinh.add(jsonObject.getString("Ten"));
//                        Toast.makeText(getContext(), arrTinh.get(0), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(getContext(), " selected", Toast.LENGTH_SHORT).show();
        switch (adapterView.getId()){
            case 0:        Toast.makeText(getContext(), " selected", Toast.LENGTH_SHORT).show(); break;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

package com.example.cinema.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.adapters.LichSuGiaoDichAdapter;
import com.example.cinema.adapters.ThongKeGiaoDichAdapter;
import com.example.cinema.models.GiaoDich;
//import com.example.vphuot.Model.Article;
//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

public class CardFragment extends Fragment {
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
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.layout_trang_ca_nhan, container, false);
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

//    public void viewdata() {
//
//        // Khởi tạo OkHttpClient để lấy dữ liệu.
//        OkHttpClient client = new OkHttpClient();
//
//        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
//        Moshi moshi = new Moshi.Builder().build();
//        Type articleType = Types.newParameterizedType(List.class, Article.class);
//        final JsonAdapter<List<Article>> jsonAdapter = moshi.adapter(articleType);
//
//        // Tạo request lên server.
//        Request request = new Request.Builder()
//                .url("http://192.168.100.55/WebApiPhuot/public/api/BaiViet")
//                .build();
//
//        // Thực thi request.
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("Error", "Network Error");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
//                String json = response.body().string();
//                final List<Article> list = jsonAdapter.fromJson(json);
//
//                // Cho hiển thị lên RecyclerView.
//               getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        rvArticles.setAdapter(new ArticleAdapter(list, getActivity()));
//                    }
//                });
//
//            }
//
//
//
//
//        });
//    }
}

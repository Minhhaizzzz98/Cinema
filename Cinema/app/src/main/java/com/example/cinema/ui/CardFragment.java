package com.example.cinema.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
//import com.example.vphuot.Model.Article;
//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

// quản lý nội dung hiển thị liên quan đến home_layout.xml
public class CardFragment extends Fragment {

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
                rootView= inflater.inflate(R.layout.layout_thong_tin_giao_dich, container, false);
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

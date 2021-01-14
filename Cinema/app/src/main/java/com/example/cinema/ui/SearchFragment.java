package com.example.cinema.ui;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.ChiTietPhim;
import com.example.cinema.R;
import com.example.cinema.adapters.AdapterListPhim;
import com.example.cinema.adapters.AdapterPhanTrang;
import com.example.cinema.adapters.MovieAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.adapters.SearchAdapter;
import com.example.cinema.api.ReadJSONE;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Movie;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    RecyclerView list, phantrang;
    AdapterListPhim adapter;
    AdapterPhanTrang adapterPhanTrang;
    SearchView editsearch;
    List<Movie> lstMovies;
    MovieItemClickListener movieItemClickListener;
    List<Integer> lstPhanTrang = new ArrayList<>();
    private int position=0, positionend = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    public SearchFragment(int position, int positionend) {
        // Required empty public constructor
        this.position = position;
        this.positionend = positionend;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Locate the ListView in listview_main.xml
        list = view.findViewById(R.id.listview);
        phantrang = view.findViewById(R.id.rcv_phantrang);
        list.setLayoutManager(new GridLayoutManager(getContext(),2));
        list.setHasFixedSize(true);

        viewdata();

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener((SearchView.OnQueryTextListener)this);
        return view;
    }

    @SuppressLint("WrongConstant")
    public void S2_Trang_S2(){
//        if(lstMovies.size()/10==0){
//            for(int j = 1; j <= lstMovies.size()/10; j++){
//                lstPhanTrang.add(j);
//            }
//        }else {
//            for(int j = 1; j <= lstMovies.size()/10+1; j++){
//                lstPhanTrang.add(j);
//            }
//        }
        for(int j = 1; j <= (lstMovies.size()+1)/4; j++){
            lstPhanTrang.add(j);
        }

        phantrang.setAdapter(new AdapterPhanTrang(getContext(),lstPhanTrang));
        phantrang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        phantrang.setHasFixedSize(true);
    }

    public void viewdata() {
        ReadJSONE readJSONE = new ReadJSONE();

        // Khởi tạo OkHttpClient để lấy dữ liệu.
        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        Type articleType = Types.newParameterizedType(List.class, Movie.class);
        final JsonAdapter<List<Movie>> jsonAdapter = moshi.adapter(articleType);

        // Tạo request lên server.
        okhttp3.Request request = new Request.Builder()
                .url("http://169.254.159.118/www/Lara_Cinema/CenimaProject/public/api/Phim")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                final List<Movie> list1;
                try {
                    list1 = readJSONE.Movie(json); lstMovies = list1;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Cho hiển thị lên RecyclerView.
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(new AdapterListPhim(getActivity(),lstMovies,SearchFragment.this::onMovieClick));
                        S2_Trang_S2();
                        List<Movie> tmp = new ArrayList<>();
                        if(positionend != 0){
                            for(int i = position; i <= positionend; i++){
                                tmp.add(lstMovies.get(i));
                            }
                            adapter = new AdapterListPhim(getContext(),tmp,SearchFragment.this::onMovieClick);
                            list.setAdapter(adapter);
                        }
                    }
                });
            }
        });
    }

    private void onMovieClick(Movie movie, ImageView imageView) {
        Intent intent=new Intent(getContext(), ChiTietPhim.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        intent.putExtra("trailer", movie.getStreamingLink());
        // create the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                imageView,"sharedName");

        startActivity(intent,options.toBundle());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    public void ShowPosition(int position,List<Movie> lstMovies, MovieItemClickListener movieItemClickListener ){
        List<Movie> tmp = new ArrayList<>();
        for(int i = position; i <= lstMovies.size(); i++){
            tmp.add(lstMovies.get(i));
        }
        adapter = new AdapterListPhim(getContext(),tmp,movieItemClickListener);
        list.setAdapter(adapter);
    }
}
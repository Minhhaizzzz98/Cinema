package com.example.cinema;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.adapters.AdapterListPhim;
import com.example.cinema.adapters.MovieAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CardPhimFragment extends Fragment implements MovieItemClickListener {
    private RecyclerView rvArticles;
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    View rootView;
    MovieItemClickListener movieItemClickListener;
    SharedPreferences sharedPreferences;
    //private RecyclerView.Adapter articleAdapter;
    private List<Movie> lstMovies= new ArrayList<>();
    public static CardPhimFragment newInstance(Integer counter){
        CardPhimFragment fragment= new CardPhimFragment();
        Bundle args= new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    public CardPhimFragment() {
    }
    // gắn layout khác nhau vào nội dung của từng tab -> trả ra view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.fragment_phim, container, false);
                break;
            case 1:
                rootView= inflater.inflate(R.layout.fragment_phim, container, false);
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
        rvArticles= view.findViewById(R.id.rv_article);




        if(counter==0){
            viewDataPhim();
        }
        else
        {
            viewDataPhim();
        }
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent=new Intent(getContext(), ChiTietPhim.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        intent.putExtra("trailer", movie.getStreamingLink());
        // create the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());
    }
    public void viewDataPhim() {
        lstMovies=new ArrayList<>();
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/Phim";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        Movie movie=new Movie();
                        //
                        movie.setId(jsonObject.getInt("id"));
                        //
                        movie.setDescription("Mô Tả 1");
                        String temp=jsonObject.get("Trailer").toString().substring(32);
                        String []teapArr=temp.split("&");
                        //
                        movie.setStreamingLink(teapArr[0]);
                        String hinh="http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/data/"+jsonObject.getString("HinhAnh");
                        //
                        movie.setHinhanh(hinh);
                        //
                        movie.setTitle(jsonObject.getString("TenPhim"));

                        JSONObject jsonObject1=jsonObject.getJSONObject("theloais");
                        movie.setLoaiPhim(jsonObject1.getString("TenLoaiPhim"));
                        lstMovies.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                AdapterListPhim movieAdapter = new AdapterListPhim(getActivity(),lstMovies,movieItemClickListener=new MovieItemClickListener() {
                    @Override
                    public void onMovieClick(Movie movie, ImageView movieImageView) {
                        sharedPreferences= getActivity().getSharedPreferences("ChonGhePrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putInt("phim_id", movie.getId());
                        editor.commit();

                        Intent intent=new Intent(getContext(), ChiTietPhim.class);
                        intent.putExtra("title",movie.getTitle());
                        intent.putExtra("imgURL",movie.getHinhanh());
                        intent.putExtra("imgCover",movie.getCoverPhoto());
                        intent.putExtra("trailer", movie.getStreamingLink());
                        // create the animation
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                                movieImageView,"sharedName");

                        startActivity(intent,options.toBundle());
                    }
                });

                rvArticles.setAdapter(movieAdapter);
                rvArticles.setLayoutManager(new GridLayoutManager(getContext(),2));
                rvArticles.setHasFixedSize(true);
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

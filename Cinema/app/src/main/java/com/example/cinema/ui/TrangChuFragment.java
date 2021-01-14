package com.example.cinema.ui;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.ChiTietPhim;
import com.example.cinema.Phim;
import com.example.cinema.R;
import com.example.cinema.adapters.MovieAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.adapters.PhimViewPagerAdapter;
import com.example.cinema.adapters.RapAdapter;
import com.example.cinema.adapters.SliderPagerAdapter;
import com.example.cinema.fragment_phim_all;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Movie;
import com.example.cinema.models.Rap;
import com.example.cinema.models.Slide;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment  implements MovieItemClickListener {

    private List<Slide> lstSlides ;
    private List<Movie> lstMovies;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private Button btnXemThem1;
    private  Button btnXemThem2;
    private RecyclerView rvDang;
    private  RecyclerView rvSap;
    private ActionBar toolbar;
    MovieItemClickListener movieItemClickListener;
    SharedPreferences sharedPreferences;
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_trang_chu, container, false);
        toolbar=((AppCompatActivity) getActivity()).getSupportActionBar();
        sliderpager = rootView.findViewById(R.id.slider_pager) ;
        rvDang=(RecyclerView)rootView.findViewById(R.id.rvDangChieu);
        rvSap=(RecyclerView)rootView.findViewById(R.id.rvSapChieu);
        indicator = rootView.findViewById(R.id.indicator);

       // MoviesRV = rootView.findViewById(R.id.Rv_movies);

        // prepare a list of slides ..
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.slide1,"WOLVERINE \nDirected by James Mangold"));
        lstSlides.add(new Slide(R.drawable.slide2,"SHERLOCK HOMES \nDirected by Guy Ritchie"));
        lstSlides.add(new Slide(R.drawable.slide1,"WOLVERINE \nDirected by James Mangold"));
        lstSlides.add(new Slide(R.drawable.slide2,"SHERLOCK HOMES \nDirected by Guy Ritchie"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(),lstSlides);
        sliderpager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);

        // Recyclerview Setup
        // ini data



        
        indicator.setupWithViewPager(sliderpager,true);


        viewDataPhim();
        btnXemThem1=(Button)rootView.findViewById(R.id.btnAllPhim);
        btnXemThem2=(Button)rootView.findViewById(R.id.btnAllPhimMoi);
        btnXemThem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle(R.string.bottom_navigation_phim);
                Fragment fragment;
                fragment= new fragment_phim_all();
                loadFragment(fragment);
            }
        });
        btnXemThem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle(R.string.bottom_navigation_phim);
                Fragment fragment;
                fragment= new fragment_phim_all();
                loadFragment(fragment);
            }
        });
        return rootView;
    }



    class SliderTimer extends TimerTask {


        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });


        }
    }
    public void viewDataPhim() {
        lstMovies=new ArrayList<>();
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/Phim";
//        String url= "http://cinemacdth18c.byethost12.com/api/Phim";

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
//                        String hinh = "http://cinemacdth18c.byethost12.com/api/Phim";
                       //
                        movie.setHinhanh(hinh);
                        //
                        movie.setTitle(jsonObject.getString("TenPhim"));

                        JSONObject jsonObject1=jsonObject.getJSONObject("theloais");
                        movie.setLoaiPhim(jsonObject1.getString("TenLoaiPhim"));
                        lstMovies.add(movie);
                        //Toast.makeText(getContext(), teapArr[0], Toast.LENGTH_SHORT).show();
//                        Rap rap= new Rap();
//                        rap.setName(jsonObject.getString("TenRap"));
//                        rap.setAddress(jsonObject.getString("DiaChi"));
//                        rap.setPhone(jsonObject.getString("SDT"));
//                        rap.setImg(R.drawable.rap);
//
//                        lstRap.add(rap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                MovieAdapter movieAdapter = new MovieAdapter(getActivity(),lstMovies,movieItemClickListener=new MovieItemClickListener() {
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

                rvSap.setAdapter(movieAdapter);
                rvSap.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                rvDang.setAdapter(movieAdapter);
                rvDang.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


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
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

    }
}
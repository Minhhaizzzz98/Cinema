package com.example.cinema.ui;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
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

import com.example.cinema.ChiTietPhim;
import com.example.cinema.R;
import com.example.cinema.adapters.MovieAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.adapters.PhimViewPagerAdapter;
import com.example.cinema.adapters.SliderPagerAdapter;
import com.example.cinema.fragment_phim_all;
import com.example.cinema.models.Movie;
import com.example.cinema.models.Slide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
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
    private ViewPager sliderpager;
    private TabLayout indicator;
    private Button btnXemThem1;
    private  Button btnXemThem2;
    private RecyclerView rvDang;
    private  RecyclerView rvSap;
    private ActionBar toolbar;
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

        List<Movie> lstMovies = new ArrayList<>();

        
        indicator.setupWithViewPager(sliderpager,true);


        lstMovies.add(new Movie("Moana",R.drawable.moana,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        lstMovies.add(new Movie("Black Panther",R.drawable.blackp,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));

        MovieAdapter movieAdapter = new MovieAdapter(getActivity(),lstMovies,this);
       rvSap.setAdapter(movieAdapter);
        rvSap.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvDang.setAdapter(movieAdapter);
        rvDang.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
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

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

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
}
package com.example.cinema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.adapters.MovieAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class CardPhimFragment extends Fragment implements MovieItemClickListener {
    private RecyclerView rvArticles;
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    View rootView;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        if(counter==0){
            lstMovies.add(new Movie("Moana",R.drawable.moana,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("Black P",R.drawable.blackp,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        }
        else
        {
            lstMovies.add(new Movie("Moana",R.drawable.moana,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("Black P",R.drawable.blackp,"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
            lstMovies.add(new Movie("The Martian",R.drawable.themartian, "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CwfoyVa980U\" frameborder=\"0\" allowfullscreen></iframe>","Hành Động"));
        }
        MovieAdapter movieAdapter = new MovieAdapter(getContext(),lstMovies,this::onMovieClick);
        rvArticles.setAdapter(movieAdapter);
        rvArticles.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvArticles.setHasFixedSize(true);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

    }
}

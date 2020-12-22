package com.example.cinema.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.adapters.ActorAdapter;
import com.example.cinema.adapters.CommentAdapter;
import com.example.cinema.adapters.NewsAdapter;
import com.example.cinema.models.Actor;
import com.example.cinema.models.Comment;
import com.example.cinema.models.News;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardDienAnhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardDienAnhFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private CommentAdapter commentAdapter;
    private ActorAdapter actorAdapter;
    private List<Actor> actors = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<News> news = new ArrayList<>();
    //more 2 list
    private static Integer counter;
    View rootView;
    private static final String ARG_COUNT = "param1";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters
    public CardDienAnhFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CardNhomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardDienAnhFragment newInstance(Integer counter){
        CardDienAnhFragment fragment= new CardDienAnhFragment();
        Bundle args= new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            counter= getArguments().getInt(ARG_COUNT);
        }
    }
    // gắn layout khác nhau vào nội dung của từng tab -> trả ra view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.fragment_card_dien_anh, container, false);
                break;
            case 1:
                rootView= inflater.inflate(R.layout.fragment_card_dien_anh, container, false);
                break;
            case 2:
                rootView= inflater.inflate(R.layout.fragment_card_dien_anh, container, false);
                break;
        }
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= view.findViewById(R.id.rcv);

        if(counter==0){

            comments.add(new Comment("Lisa", R.drawable.mov2));
            comments.add(new Comment("Lisa", R.drawable.mov2));
            comments.add(new Comment("Lisa", R.drawable.mov2));
            comments.add(new Comment("Lisa", R.drawable.mov2));


            commentAdapter= new CommentAdapter(getActivity(), comments);
            recyclerView.setHasFixedSize(true);
            // LinearLayoutManager quản lý hiển thị của RecycleView
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            // đổ LayoutManager và Adapter vào RecycleView
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(commentAdapter);
        }
        else if(counter==1){
            news.add(new News("Lisa", R.drawable.mov2));
            news.add(new News("Lisa", R.drawable.mov2));
            news.add(new News("Lisa", R.drawable.mov2));
            news.add(new News("Lisa", R.drawable.mov2));


            newsAdapter= new NewsAdapter(getActivity(), news);
            recyclerView.setHasFixedSize(true);
            // LinearLayoutManager quản lý hiển thị của RecycleView
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            // đổ LayoutManager và Adapter vào RecycleView
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(newsAdapter);
        }
        else
        {
            actors.add(new Actor(R.drawable.mov2,"", ""));
            actors.add(new Actor(R.drawable.mov2,"", ""));
            actors.add(new Actor(R.drawable.mov2,"", ""));
            actors.add(new Actor(R.drawable.mov2,"", ""));


            actorAdapter= new ActorAdapter(getActivity(), actors);
            recyclerView.setHasFixedSize(true);
            // LinearLayoutManager quản lý hiển thị của RecycleView
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            // đổ LayoutManager và Adapter vào RecycleView
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(actorAdapter);
        }
    }
}
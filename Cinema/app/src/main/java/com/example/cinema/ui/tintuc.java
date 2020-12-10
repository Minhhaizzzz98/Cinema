package com.example.cinema.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.adapters.NewsAdapter;
import com.example.cinema.models.News;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tintuc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tintuc extends Fragment {

    RecyclerView recyclerView;
    List<News> newsList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tintuc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tintuc.
     */
    // TODO: Rename and change types and number of parameters
    public static tintuc newInstance(String param1, String param2) {
        tintuc fragment = new tintuc();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tintuc, container, false);

        recyclerView = view.findViewById(R.id.rcv_tintuc);

        newsList = new ArrayList<>();
        newsList.add(new News("Top phim hay số 1 thế giới, top 1 phòng vé",R.drawable.available));
        newsList.add(new News("Top phim hay số 1 thế giới, top 1 phòng vé",R.drawable.available));
        newsList.add(new News("Top phim hay số 1 thế giới, top 1 phòng vé",R.drawable.available));
        newsList.add(new News("Top phim hay số 1 thế giới, top 1 phòng vé",R.drawable.available));
        newsList.add(new News("Top phim hay số 1 thế giới, top 1 phòng vé",R.drawable.available));
        newsList.add(new News("Top phim hay số 1 thế giới, top 1 phòng vé",R.drawable.available));

        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsList);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
package com.example.cinema.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.cinema.R;
import com.example.cinema.adapters.ListViewAdapter;
import com.example.cinema.models.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] animalNameList;
    ArrayList<Movie> arraylist = new ArrayList<Movie>();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        animalNameList = new String[]{"Lion", "Tiger", "Dog",
                "Cat", "Tortoise", "Rat", "Elephant", "Fox",
                "Cow","Donkey","Monkey","Lion", "Tiger", "Dog",
                "Cat", "Tortoise", "Rat", "Elephant", "Fox",
                "Cow","Donkey","Monkey"};

        // Locate the ListView in listview_main.xml
        list = (ListView)view.findViewById(R.id.listview);

        for (int i = 0; i < animalNameList.length; i++) {
            Movie animalNames = new Movie(animalNameList[i],"",1,"","","");
            // Binds all strings into an array
            arraylist.add(animalNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getContext(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener((SearchView.OnQueryTextListener)this);

        return view;
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
}
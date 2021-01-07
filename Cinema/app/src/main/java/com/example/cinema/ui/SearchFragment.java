package com.example.cinema.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.cinema.adapters.MovieAdapter;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.adapters.SearchAdapter;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    RecyclerView list;
    AdapterListPhim adapter;
    SearchView editsearch;
//    String[] animalNameList;
//    ArrayList<Movie> arraylist = new ArrayList<Movie>();
    private List<Movie> lstMovies;
    MovieItemClickListener movieItemClickListener;

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

        // Locate the ListView in listview_main.xml
        list = view.findViewById(R.id.listview);
        viewDataPhim();

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
                    adapter = new AdapterListPhim(getContext(),lstMovies,this::onMovieClick);
                    list.setAdapter(adapter);
                    list.setLayoutManager(new GridLayoutManager(getContext(),2));
                    list.setHasFixedSize(true);

                }
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
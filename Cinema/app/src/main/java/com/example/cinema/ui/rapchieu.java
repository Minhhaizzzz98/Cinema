package com.example.cinema.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.R;
import com.example.cinema.adapters.RapAdapter;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Rap;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rapchieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rapchieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinkedList<Rap> lstRap;
    RecyclerView rvRap;
    RapAdapter rapAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rapchieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rapchieu.
     */
    // TODO: Rename and change types and number of parameters
    public static rapchieu newInstance(String param1, String param2) {
        rapchieu fragment = new rapchieu();
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
//        lstRap=new LinkedList<>();
//        lstRap.add(new Rap(R.drawable.slide1,"Galaxy Tân Bình","123, Trường chinh, tân bình","123456789"));
//        lstRap.add(new Rap(R.drawable.slide2,"Galaxy Quận 1","123, Trường chinh, tân bình","123456789"));
//        lstRap.add(new Rap(R.drawable.slide1,"Galaxy Quận 3","123, Trường chinh, tân bình","123456789"));
//        lstRap.add(new Rap(R.drawable.slide2,"Galaxy Củ Chi","123, Trường chinh, tân bình","123456789"));
//        lstRap.add(new Rap(R.drawable.slide1,"Galaxy Bình Dương","123, Trường chinh, tân bình","123456789"));
//        lstRap.add(new Rap(R.drawable.slide2,"Galaxy Bình Thuận","123, Trường chinh, tân bình","123456789"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rapchieu, container, false);
        rvRap=(RecyclerView)view.findViewById(R.id.rvRap);
        rvRap.setLayoutManager( new GridLayoutManager(getContext(),1));
        viewdata();
        rvRap.setHasFixedSize(true);

        // Inflate the layout for this fragment
        return view;
    }

    public void viewdata() {
        String url= "http://"+ DataHelperConnnect.ipConnect+"/api/Rap";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                lstRap=new LinkedList<>();
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        Rap rap= new Rap();
                        rap.setName(jsonObject.getString("TenRap"));
                        rap.setAddress(jsonObject.getString("DiaChi"));
                        rap.setPhone(jsonObject.getString("SDT"));
                        rap.setImg(R.drawable.rap);

                        lstRap.add(rap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    rapAdapter=new RapAdapter(getContext(),lstRap);
                    rapAdapter.notifyDataSetChanged();
                    rvRap.setAdapter(rapAdapter);
                }

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
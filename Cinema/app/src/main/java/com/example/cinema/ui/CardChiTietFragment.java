package com.example.cinema.ui;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.R;
import com.example.cinema.adapters.ActorAdapter;
import com.example.cinema.adapters.DienVien_DaoDienAdapter;
import com.example.cinema.adapters.GioChieuAdapter;
import com.example.cinema.adapters.GioItemClick;
import com.example.cinema.adapters.LichChieuAdapter;
import com.example.cinema.adapters.LichItemClick;
import com.example.cinema.adapters.MovieItemClickListener;
import com.example.cinema.adapters.RapAdapter;
import com.example.cinema.adapters.SpinnerRapAdapter;
import com.example.cinema.models.Actor;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.DatabaseHandler;
import com.example.cinema.models.GioChieu;
import com.example.cinema.models.Lich;
import com.example.cinema.models.Movie;
import com.example.cinema.models.Rap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CardChiTietFragment  extends Fragment {
    private static final String ARG_COUNT = "param1";
    private static Integer counter;
    private int rap_chon=1;
    SharedPreferences sharedpreferences;
    SharedPreferences mypre;
    public static final String MyPREFERENCES = "ChonGhePrefs" ;
    public static final String MYPRE = "MyPrefs" ;
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    View rootView;
    private MovieItemClickListener movieItemClickListener;
    private LichItemClick lichItemClick;
    private GioItemClick gioItemClick;
    RecyclerView rvDienVien, rvDaoDien;
    LinkedList<Rap> lstRap;
    LinkedList<Actor> actorlist= new LinkedList<Actor>();
    LinkedList<Actor> directorlist= new LinkedList<Actor>();
    Rap user;
    //Khoi tạo textView Rap;
    TextView txtRap;
    Button btnDat;
    RecyclerView rvGio;
    Lich lich= new Lich();
    public CardChiTietFragment() {
    }
    public static CardChiTietFragment newInstance(Integer counter){
        CardChiTietFragment fragment= new CardChiTietFragment();
        Bundle args= new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            counter= getArguments().getInt(ARG_COUNT);
        }
        lstRap=new LinkedList<>();
        //createListRap();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (getArguments().getInt(ARG_COUNT)){
            case 0:
                rootView= inflater.inflate(R.layout.chitiet_datve, container, false);
                //Text view show khi click
                sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                mypre = getActivity().getSharedPreferences(MYPRE, Context.MODE_PRIVATE);
                TextView txtNgayThang=rootView.findViewById(R.id.txtNgayChieu);
                txtRap=rootView.findViewById(R.id.txtRap);
                btnDat=rootView.findViewById(R.id.btnDatNgay);
                SpinnerRapAdapter spinnerRapAdapter=new SpinnerRapAdapter(getContext(),android.R.layout.simple_spinner_item,lstRap);
                Spinner spinner = (Spinner)rootView.findViewById(R.id.diadiem_spinner);
                Spinner spinnerRap=(Spinner)rootView.findViewById(R.id.rap_spinner);
                 rvGio=(RecyclerView)rootView.findViewById(R.id.rv_giochieu);

                // Create an ArrayAdapter using the string array and a default spinner layout
                loadSpinDiaDiem(spinner,R.array.diadiem_array);
                //loadSpinDiaDiem(spinnerRap,R.array.rap_array);
                //spinnerRap.setAdapter(spinnerRapAdapter);
               // createAdapterGio(rvGio,createGio());

                RecyclerView rvLich=(RecyclerView)rootView.findViewById(R.id.rv_lich);
                LichChieuAdapter lichChieuAdapter=new LichChieuAdapter(getContext(),createLich(),lichItemClick=new LichItemClick() {
                    @Override
                    public void onLichClick(Lich movie, RelativeLayout relativeLayout) {

                        //Toast.makeText(getContext(), movie.getThu()+"", Toast.LENGTH_SHORT).show();
                        String thu=movie.getThu();
                        lich.setNgay(movie.getNgay());
//                        Toast.makeText(getContext(), lich.getNgay(), Toast.LENGTH_SHORT).show();
                        String[] ngay=movie.getNgay().split("/");
                        thu=thu+","+"ngày "+ngay[0]+" tháng "+ngay[1]+" Năm " + Calendar.getInstance().get(Calendar.YEAR);
                        txtNgayThang.setText(thu);
                        layDataKhachHangDN(rap_chon+"",sharedpreferences.getInt("phim_id", -1) + "",formatChuoi(movie.getNgay()));

                    }
                });
                rvLich.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                rvLich.setAdapter(lichChieuAdapter);


                viewdata(spinnerRapAdapter,spinnerRap);
                layDataKhachHangDN(rap_chon+"",sharedpreferences.getInt("phim_id", -1) +"",formatNgay());
                btnDat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplication(),ChonGheActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                rootView= inflater.inflate(R.layout.chi_tiet_thongtin, container, false);

                rvDienVien= rootView.findViewById(R.id.rvDienVien);
                rvDaoDien= rootView.findViewById(R.id.rvDaoDien);

                actorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                actorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                actorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));

                directorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                directorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));
                directorlist.add(new Actor(R.drawable.blackp, "Minh Hai"));

                DienVien_DaoDienAdapter actorAdapter = new DienVien_DaoDienAdapter(getContext(), actorlist);
                rvDienVien.setAdapter(actorAdapter);
                rvDienVien.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                DienVien_DaoDienAdapter directorAdapter = new DienVien_DaoDienAdapter(getContext(), actorlist);
                rvDaoDien.setAdapter(directorAdapter);
                rvDaoDien.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

                break;
            case 2:
                rootView= inflater.inflate(R.layout.chitiet_tintuc, container, false);
                break;
        }
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadSpinDiaDiem(Spinner spin, int n)
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                n, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spin.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Lich> createLich()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = "" + mdformat.format(calendar.getTime());

        String[] ngay=strDate.split("/");
        List<Lich> lstLich=new ArrayList<>();
       // lstLich.add(new Lich("Chủ Nhật",strDate));
        for(int i=0;i<6;i++)
        {
            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(ngay[2]), Integer.parseInt(ngay[1]), Integer.parseInt(ngay[0]+i), 0, 0);
            lstLich.add(new Lich(c.DAY_OF_WEEK+"",(Integer.parseInt(ngay[0])+i)+"/"+ngay[1]+"/"+ngay[2]));
        }
        return  lstLich;
    }
    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    private  void createAdapterGio(RecyclerView rv,List<GioChieu> lst)
    {
        GioChieuAdapter gioChieuAdapter=new GioChieuAdapter(lst,getContext(),gioItemClick=new GioItemClick() {
            @Override
            public void onGioClick(GioChieu movie, RelativeLayout relativeLayout) {
                if (!isLoggedIn()) {
                    //here, pref is the instance of your preference manager
                    Intent intent = new Intent(getContext(), Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("id", movie.getId_suat()+"");
                    editor.putString("gio",movie.getThoigian());
                    editor.putString("id_gio",movie.getId()+"");
                    editor.putString("id_phong",movie.getId_phong()+"");
                    editor.putString("ten_rap", user.getName());
                    editor.putString("ngay_chieu", lich.getNgay());
                    editor.commit();
                    Intent mIntent = new Intent(getContext(), ChonGheActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("id_suat", movie.getId_suat()+"");
                    mIntent.putExtras(mBundle);
                    startActivity(mIntent);
                }

            }
        });
        gioChieuAdapter.notifyDataSetChanged();
        rv.setLayoutManager(new GridLayoutManager(getContext(),4));
        rv.setAdapter(gioChieuAdapter);
    }
    public void viewdata(SpinnerRapAdapter adapter,Spinner spinner) {
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/Rap";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        Rap rap= new Rap();
                        rap.setId(jsonObject.getInt("id"));
                        rap.setName(jsonObject.getString("TenRap"));

                        lstRap.add(rap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        // Here you get the current item (a User object) that is selected by its position
                        user = adapter.getItem(position);
                        rap_chon=user.getId();
                        // Here you can do the action you want to...
                        Toast.makeText(getContext(), "ID: " + user.getId() + "\nName: " + user.getName(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapter) {  }
                });

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
    public void sendData1(String data){
        final String saveData= data;
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/SuatChieu/getGioChieu";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    JSONArray jsonArray=null;
                    JSONObject jsonObject= null;

                    try {
                        jsonArray=new JSONArray(response);
                        List<GioChieu> lstGio1=new ArrayList<>();
                       for(int i=0;i<jsonArray.length();i++)
                       {

                           jsonObject = jsonArray.getJSONObject(i);
                           //int id       = jsonObject.getInt("giochieu_id");
                           int id_suat=jsonObject.getInt("id");
                           String GioBatDau= jsonObject.getString("GioBatDau");
                           int id_phong=jsonObject.getInt("phong_id");
                           int id=jsonObject.getInt("giochieu_id");
                           GioChieu gc=new GioChieu();
                           gc.setId(id);
                           gc.setId_suat(id_suat);
                           gc.setId_phong(id_phong);
                           gc.setThoigian(GioBatDau);
                           lstGio1.add(gc);
                       }

                        createAdapterGio(rvGio,lstGio1);
//                        Toast.makeText(getActivity(),jsonArray.toString(),Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    //Log.i("VOLLEY", response);
                }





        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                //Log.v("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return saveData == null ? null : saveData.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }

        };
        requestQueue.add(stringRequest);
    }
    public void layDataKhachHangDN(String id_rap,String id_phim,String Ngay){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("rap_id", id_rap);
            jsonObject.put("phim_id", id_phim);
            jsonObject.put("NgayChieu",Ngay);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendData1(jsonObject.toString());
    }
    private String formatNgay()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = "" + mdformat.format(calendar.getTime());
        return  strDate;
    }
    private String formatChuoi(String chuoi)
    {
        String temp[]=chuoi.split("/");
        String strTemp=temp[2]+"-"+temp[1]+"-"+temp[0];
        return  strTemp;

    }
    public boolean isLoggedIn() {
        return mypre.getBoolean(KEY_IS_LOGGED_IN, false);//false is the default value in case there's nothing found with the key
    }
}

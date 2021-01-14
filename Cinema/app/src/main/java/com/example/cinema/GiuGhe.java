package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Ghe;
import com.example.cinema.models.GioChieu;
import com.example.cinema.ui.ChonGheActivity;
import com.example.cinema.ui.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GiuGhe extends AppCompatActivity {
    TextView counttime, txtGhe, txtRap, txtPhim, txtNgayChieu, txtGioChieu, txtPhong, txtTongTien;
    public int counter=10000;
    String ghe= new String();
    ArrayList<Integer> selectedButtons = new ArrayList<Integer>();
    ArrayList<String> selectedStr = new ArrayList<>();
    LinkedList<Ghe> lstGhe=new LinkedList<>();

    Button btnXacNhan;
    SharedPreferences sharedpreferences, myPre;
    public static final String MyPREFERENCES = "ChonGhePrefs" ;
    public static final String MyPRE = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giu_ghe);
        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Đặt ghế");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        myPre = getSharedPreferences(MyPRE, Context.MODE_PRIVATE);
        Bundle bun= getIntent().getExtras();
        selectedButtons= bun.getIntegerArrayList("LIST_GHE");
        selectedStr=bun.getStringArrayList("lst_str_ghe");
        txtGhe= findViewById(R.id.txtGhe);
        txtRap= findViewById(R.id.txtRap);
        txtPhim= findViewById(R.id.txtPhim);
        txtNgayChieu= findViewById(R.id.txtNgayChieu);
        txtGioChieu= findViewById(R.id.txtGioChieu);
        txtPhong= findViewById(R.id.txtPhong);
        txtTongTien= findViewById(R.id.txtTongTien);
        txtTongTien.setText(bun.getInt("TONGTIEN")+"");

        txtRap.setText(sharedpreferences.getString("ten_rap", null));
        txtPhim.setText(sharedpreferences.getString("ten_phim", null));
        txtNgayChieu.setText(sharedpreferences.getString("ngay_chieu", null));
        txtGioChieu.setText(sharedpreferences.getString("gio", null));
        txtPhong.setText(sharedpreferences.getString("id_phong", null));
//        txtRap.setText(sharedpreferences.getString("ten_rap", null));

        btnXacNhan=(Button)findViewById(R.id.btnXacNhan);
        counttime=findViewById(R.id.counttime);
        new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                counttime.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                //counter--;
            }
            @Override
            public void onFinish() {
                counttime.setText("Kết thúc giữ vé");
            }
        }.start();

        for(int i=0; i<selectedButtons.size(); i++){
            ghe+= selectedButtons.get(i).toString();
        }
        String chuoiGhe="";
        for(int i=0;i<selectedStr.size();i++)
        {
            if(i==0)
            {
                chuoiGhe+=selectedStr.get(i);
            }
            else
            {
                chuoiGhe+=","+selectedStr.get(i);
            }
        }

        txtGhe.setText(chuoiGhe);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=sharedpreferences.getString("id_phong","-1");
                viewdata(id);
                Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    public void viewdata(String id) {
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/Ghe/"+id;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        com.example.cinema.models.Ghe ghe=new Ghe();
                        ghe.setId(jsonObject.getInt("id"));
                        ghe.setDay(jsonObject.getString("Day"));
                        ghe.setSort(jsonObject.getInt("sort")+"");

                        lstGhe.add(ghe);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0;i<lstGhe.size();i++)
                {
                    for(int j=0;j<selectedStr.size();j++)
                    {
                        String strArr[]=selectedStr.get(j).split("");
                        if(lstGhe.get(i).getDay().equals(strArr[1])&&lstGhe.get(i).getSort().equals(strArr[2]))
                        {
                            int id_kh= myPre.getInt("id", -1);
                            String suat=sharedpreferences.getString("id","-1");
                            layDataVe(suat,lstGhe.get(i).getId()+"",id_kh+"");
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
    public void sendDataVe(String data){
        final String saveData= data;
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/Ve";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray=null;
                JSONObject jsonObject= null;
                Toast.makeText(getApplicationContext(),"Đặt vé thành công",Toast.LENGTH_LONG).show();

                //Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

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
    public void layDataVe(String suatchieu_id,String ghe_id,String kh_id){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("suatchieu_id", suatchieu_id);
            jsonObject.put("ghe_id", ghe_id);
            jsonObject.put("kh_id",kh_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendDataVe(jsonObject.toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    // Xu ly tung item trong Context Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.example.cinema.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.R;
import com.example.cinema.adapters.RapAdapter;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.KhachHang;
import com.example.cinema.models.Rap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Login extends AppCompatActivity {
    EditText editPhone, editPass;
    Button btnDangNhap;
    Intent in;

    LinkedList<KhachHang> lstKH= new LinkedList<>();
    KhachHang khachHang= new KhachHang();

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Phone = "phoneKey";
    public static final String Password = "passwordKey";
    public static final String DiaChi = "address";
    public static final String Email = "email";
    public static final String Hoten = "fullName";
    public static final String TenTK = "username";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Đăng nhập");

        // Khởi tạo các View
        editPhone= findViewById(R.id.txtsdt);
        editPass= findViewById(R.id.txtmatkhau);
        btnDangNhap= findViewById(R.id.btnDangNhap);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag= false;
                String phone  = editPhone.getText().toString();
                String pass  = editPass.getText().toString();

                layDanhSachKH();
                for(int i= 0; i< lstKH.size(); i++){
                    if(lstKH.get(i).getSDT().equals(phone) && lstKH.get(i).getPassword().equals(pass)){
                        flag=true;
                        khachHang= lstKH.get(i);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(Phone, phone);
                        editor.putString(Password, pass);
                        editor.putString(DiaChi, lstKH.get(i).getDiaChi());
                        editor.putString(Email, lstKH.get(i).getEmail());
                        editor.putString(Hoten, lstKH.get(i).getHoTen());
                        editor.putString(TenTK, lstKH.get(i).getTenTK());
                        editor.putBoolean(KEY_IS_LOGGED_IN, true);
                        editor.commit();

                        break;
                    }
                }
                if(flag){
                    in = new Intent(Login.this, QuanLyThongTin.class);
                    Bundle bundle= new Bundle();
                    bundle.putString("HOTEN", khachHang.getHoTen());
                    bundle.putString("TENTK", khachHang.getTenTK());
                    bundle.putString("DIACHI", khachHang.getDiaChi());
                    bundle.putString("SDT", khachHang.getSDT());
                    bundle.putString("EMAIL", khachHang.getEmail());
                    bundle.putString("PASSWORD", khachHang.getPassword());

                    in.putExtras(bundle);
                    startActivity(in);
                }
                else Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (isLoggedIn()) {
            //here, pref is the instance of your preference manager
            Intent intent = new Intent(this, QuanLyThongTin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            finish();
        }
    }
    public boolean isLoggedIn() {
        return sharedpreferences.getBoolean(KEY_IS_LOGGED_IN, false);//false is the default value in case there's nothing found with the key
    }

    public void layDanhSachKH() {
        String url= "http://"+ DataHelperConnnect.ipConnect+"/lara_cinema/CenimaProject/public/api/KhachHang";
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        KhachHang kh= new KhachHang();
                        kh.setSDT(jsonObject.getString("SDT"));
                        kh.setPassword(jsonObject.getString("password"));
                        kh.setId(jsonObject.getInt("id"));
                        kh.setHoTen(jsonObject.getString("HoTen"));
                        kh.setTenTK(jsonObject.getString("TenTK"));
                        kh.setEmail(jsonObject.getString("Email"));
                        kh.setDiaChi(jsonObject.getString("DiaChi"));

                        lstKH.add(kh);

                    } catch (JSONException e) {
                        e.printStackTrace();
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

    public void chuyendangky(View view) {
        Intent intent = new Intent(this,DangKy.class);
        startActivity(intent);
    }
    // Khoi tao Context Menu
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return super.onCreateOptionsMenu(menu);

    }


    // Xu ly tung item trong Context Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:
                Toast.makeText(this, "Notification button selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact:
                Toast.makeText(this, "Contact selected", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
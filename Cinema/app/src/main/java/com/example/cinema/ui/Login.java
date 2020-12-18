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

import com.example.cinema.R;

public class Login extends AppCompatActivity {
    EditText editPhone, editPass;
    Button btnDangNhap;
    Intent in;

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Phone = "phoneKey";
    public static final String Password = "passwordKey";
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
                String phone  = editPhone.getText().toString();
                String pass  = editPass.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Phone, phone);
                editor.putString(Password, pass);
                editor.putBoolean(KEY_IS_LOGGED_IN, true);
                editor.commit();

                in = new Intent(Login.this, QuanLyThongTin.class);
                startActivity(in);
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
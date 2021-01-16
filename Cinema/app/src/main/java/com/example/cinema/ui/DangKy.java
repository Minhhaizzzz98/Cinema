package com.example.cinema.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.R;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.KhachHang;
import com.google.android.gms.common.api.internal.DataHolderNotifier;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class DangKy extends AppCompatActivity {
    TextView date;
    DatePickerDialog datePickerDialog;
    EditText editPhone, editFullName, editAddress, editEmail, editPass, editRePass;
    Button dangky;
    public static int RESULT_LOAD_IMAGE = 1;
    KhachHang khachHang= new KhachHang();

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Phone = "phoneKey";
    public static final String Password = "passwordKey";
    public static final String DiaChi = "address";
    public static final String Email = "email";
    public static final String Hoten = "fullName";
    public static final String NgaySinh = "birthday";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Đăng ký");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        dangky= findViewById(R.id.dangky);

        // initiate the date picker and a button
        date =  findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(DangKy.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPhone= findViewById(R.id.editPhone);
                editAddress= findViewById(R.id.editAddress);
                editFullName= findViewById(R.id.editFullName);
                editEmail= findViewById(R.id.editEmail);
                editPass= findViewById(R.id.editPass);
                editRePass= findViewById(R.id.editRePass);

                String phone= editPhone.getText().toString();
                String email= editEmail.getText().toString();
                String fullName= editFullName.getText().toString();
                String pass= editPass.getText().toString();
                String rePass= editRePass.getText().toString();
                String ngaySinh= date.getText().toString();
                String diaChi= editAddress.getText().toString();

                if(kiemTraNhap(phone, email, fullName, pass, rePass)){
                    if(kiemTraMatKhau(pass, rePass)){
                        khachHang.setSDT(phone);
                        khachHang.setHoTen(fullName);
                        khachHang.setEmail(email);
                        khachHang.setPassword(pass);
                        khachHang.setNgaySinh(ngaySinh);
                        khachHang.setDiaChi(diaChi);

                        layDataKhachHangDN(khachHang);
                    }
                    else Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại sai", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "Cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void chuyendangnhap(View view) {
        Intent intent = new Intent(this, Login.class);
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

    public boolean kiemTraNhap(String phone, String email, String fullName, String pass, String rePass){
        boolean flag=true;
        if(phone.trim().length()==0){
            Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
            flag=false;
        }else if(fullName.trim().isEmpty()){
            Toast.makeText(this, "Họ tên không hợp lệ", Toast.LENGTH_SHORT).show();
            flag=false;
        }else if(pass.trim().isEmpty()){
            Toast.makeText(this, "Mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
            flag=false;
        }else if(rePass.trim().isEmpty()){
            Toast.makeText(this, "Nhập lại mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
            flag=false;
        }else if(email.trim().isEmpty()){
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            flag=false;
        }
        return flag;
    }
    public boolean kiemTraMatKhau(String pass, String rePass){
        if(pass.equals(rePass))
            return true;
        else return false;
    }
    public void layDataKhachHangDN(KhachHang kh){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("hoten", kh.getHoTen());
            jsonObject.put("pass", kh.getPassword());
            jsonObject.put("sdt", kh.getSDT());
            jsonObject.put("ngaysinh", kh.getNgaySinh());
            jsonObject.put("email", kh.getEmail());
            jsonObject.put("diachi", kh.getDiaChi());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendData(jsonObject.toString(), kh.getPassword());
    }
    public void sendData(String data, String pass){
        final String saveData= data;
        String url= "http://"+ DataHelperConnnect.ipConnect+"/api/KhachHang_Dk";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("false")) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Phone, khachHang.getSDT());
                    editor.putString(Password, pass);
                    editor.putString(DiaChi, khachHang.getDiaChi());
                    editor.putString(Email, khachHang.getEmail());
                    editor.putString(Hoten, khachHang.getHoTen());
                    editor.putString(NgaySinh, khachHang.getNgaySinh());
                    editor.putBoolean(KEY_IS_LOGGED_IN, true);
                    editor.commit();

                    Intent in = new Intent(DangKy.this, QuanLyThongTin.class);
                    startActivity(in);
                } else
                    Toast.makeText(getApplicationContext(), "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
            }
            //Log.i("VOLLEY", response);

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            ImageView imageView = (ImageView) findViewById(R.id.ivAvatar);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            cursor.close();
        }
    }

    public void UploadHinh(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
}
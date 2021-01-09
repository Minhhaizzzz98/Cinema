package com.example.cinema.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cinema.GiuGhe;
import com.example.cinema.R;
import com.example.cinema.adapters.SpinnerRapAdapter;
import com.example.cinema.adapters.SpinnerThoiGianAdapter;
import com.example.cinema.models.DataHelperConnnect;
import com.example.cinema.models.Ghe;
import com.example.cinema.models.GioChieu;
import com.example.cinema.models.Rap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChonGheActivity extends AppCompatActivity {
    Boolean[] btnSelected= new Boolean[21]; // array to store selected or not selected status of each seat
    final ArrayList<Integer> selectedButtons = new ArrayList<Integer>();
    final ArrayList<String> selectString=new ArrayList<>();
    int noOfSelectedSeats;
    Spinner spinner;
    private int tongTien=0;
    private TextView txtTongTien;
    LinkedList<Ghe> lstGhe=new LinkedList<>();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "ChonGhePrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String suat=sharedpreferences.getString("id","-1");
        String id_phong=sharedpreferences.getString("id_phong","-1");
        String id_gio=sharedpreferences.getString("id_gio","-1");
        String gio=sharedpreferences.getString("gio","-1");
        Toast.makeText(this, suat+" ---- "+id_phong+"--------"+id_gio+"------------"+gio, Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);
        Bundle bundle = getIntent().getExtras();
        final String id_suat = bundle.getString("id_suat");
     //  Toast.makeText(this, "hai+ "+id_suat, Toast.LENGTH_SHORT).show();
        // tạo spinner
        spinner = (Spinner) findViewById(R.id.thoigian_spinner);
        txtTongTien=findViewById(R.id.txtTongTien);
        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Chọn ghế");
        createSpinner();
//
//        Bundle bundle = getIntent().getExtras();
//        final int infants = bundle.getInt("infants");
//        final int adults = bundle.getInt("adults");
//        final int seniors = bundle.getInt("seniors");
//
//        Toast.makeText(this, infants+"", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, seniors+"", Toast.LENGTH_SHORT).show();
//
//        Toast.makeText(this, adults+"", Toast.LENGTH_SHORT).show();
//
        //final int totalTickets=infants+adults+seniors;
        final int totalTickets=10;

        Button[] seatButtons = new Button[21];

        final ArrayList<Integer> bookedButtons = new ArrayList<Integer>();
        bookedButtons.add(1);
        bookedButtons.add(2);
        bookedButtons.add(11);

        if (bookedButtons != null) {
            for (int i = 0; i < bookedButtons.size(); i++) {

                String buttonID = "btn"+bookedButtons.get(i);
                Log.d("bookedseatno",buttonID+" ----  "+bookedButtons.get(i));
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button btn = (Button) (findViewById(resID));
                btn.setBackground(getDrawable(R.drawable.booked));
                btn.setEnabled(false);
            }
        }
//         set btnselected flag as false for all buttons
        for(int i=0;i<21;i++){
            btnSelected[i]=false;
        }

        for (int i = 1; i < seatButtons.length; i++) {

            int m = i;
            String buttonID = "btn" + m;

            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Log.d("buttonid", "idd---" + buttonID);
            Log.d("resId", "idd---" + resID);

            seatButtons[i] = ((Button) findViewById(resID));
            final int finalId = resID;
            final Integer buttonNum = i;

            //set listeners to all buttons
            if (seatButtons[i].isEnabled()) {

                seatButtons[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        int btnId = finalId;
                        Button btn = (Button) findViewById(btnId);
                        if (!btnSelected[buttonNum]) {
                            btn.setBackground(getResources().getDrawable(R.drawable.selected));
                            btnSelected[buttonNum] = true;
//                            noOfSelectedSeats=noOfSelectedSeats+1;
                            tongTien=tongTien+50000;
                            selectString.add(btn.getText().toString());
                            selectedButtons.add(buttonNum);
                            char a='b';
                            char b='a';
                            // String kk[]=btn.getText().toString().charAt();
                            a=btn.getText().toString().charAt(0);
                            b=btn.getText().toString().charAt(1);
                            Toast.makeText(ChonGheActivity.this, a+"---------"+b, Toast.LENGTH_SHORT).show();
                            lstGhe.add(new Ghe(a+"",b+""));

                        } else {
                            btn.setBackground(getResources().getDrawable(R.drawable.available));
                            btnSelected[buttonNum] = false;
//                            noOfSelectedSeats=noOfSelectedSeats-1;
                            tongTien=tongTien-50000;
                            char a='b';
                            char b='a';
                            String day=a+"";
                            String sort=b+"";
                            a=btn.getText().toString().charAt(0);
                            b=btn.getText().toString().charAt(1);
                            try {
                                selectedButtons.remove(buttonNum);
                                selectString.remove(btn.getText().toString());
                                for(Ghe ghe:lstGhe)
                                {
                                    if(ghe.getDay().equals(day)&&ghe.getSort().equals(sort))
                                    {
                                        lstGhe.remove(ghe);
                                    }
                                }
                            }
                            catch(Exception ex)
                            {
                                System.out.println(ex);
                            }

                        }


                        txtTongTien.setText(tongTien+"");

                    }
                });

            }
// go to Receipt page i.e. Payment Activity after selecting seats and confirming seats

            Button btnProceed = (Button) findViewById(R.id.btnConfirmSeats);
            btnProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedButtons.size()==0){
                        Toast.makeText(getApplicationContext(),"Mời bạn chọn ghế!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent=new Intent(ChonGheActivity.this, GiuGhe.class);
                        Bundle bun= new Bundle();
                        bun.putIntegerArrayList("LIST_GHE", selectedButtons);
                        bun.putStringArrayList("lst_str_ghe",selectString);
                        intent.putExtras(bun);
                        startActivity(intent);
                    }

                }
            });
//            btnProceed.setOnClickListener(new View.OnClickListener() {
//
//
//                public void onClick(View v) {
//                    if (noOfSelectedSeats > totalTickets) {
//                        Toast.makeText(getApplicationContext(), "Please select > of seats as selected previously", Toast.LENGTH_LONG).show();
//                    }
//                    else if (noOfSelectedSeats < totalTickets) {
//                        Toast.makeText(getApplicationContext(), "Please select < of seats as selected previously", Toast.LENGTH_LONG).show();
//                    }
//                    else  {
//
//
//
//                        Intent myintent = new Intent(getApplicationContext(), PaymentActivity.class);
//                        myintent.putExtra("selectedSeatsList",selectedButtons);
//                        myintent.putExtra("movieTitle", getIntent().getExtras().getString("title"));
////                      myintent.putExtra("movieId",movieId);
////                      myintent.putExtra("movieStartTime",movieStartTime);
//                        myintent.putExtra("infants",infants);
//                        myintent.putExtra("adults",adults);
//                        myintent.putExtra("seniors",seniors);
//                        myintent.putExtra("movieDate",getIntent().getExtras().getString("date"));
//
//                        startActivity(myintent);
//                    }
//                }
//
//            });
        }
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
    private LinkedList<GioChieu> createGio()
    {
        LinkedList<GioChieu> lstGio=new LinkedList<>();
        lstGio.add(new GioChieu(1,"20:00"));
        lstGio.add(new GioChieu(2, "18:00"));
        lstGio.add(new GioChieu(3,"16:00"));
        lstGio.add(new GioChieu(4 ,"15:00"));

        return  lstGio;
    }
    private void createSpinner()
    {

// Create an ArrayAdapter using the string array and a default spinner layout
        SpinnerThoiGianAdapter spinnerThoiGianAdapter=new SpinnerThoiGianAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,createGio());
        spinner.setAdapter(spinnerThoiGianAdapter);
    }

}
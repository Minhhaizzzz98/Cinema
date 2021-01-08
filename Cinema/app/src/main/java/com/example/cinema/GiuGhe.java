package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.ArrayList;

public class GiuGhe extends AppCompatActivity {
    TextView counttime, txtGhe;
    public int counter=10000;
    String ghe= new String();
    ArrayList<Integer> selectedButtons = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giu_ghe);

        Bundle bun= getIntent().getExtras();
        selectedButtons= bun.getIntegerArrayList("LIST_GHE");

        txtGhe= findViewById(R.id.txtGhe);
        counttime=findViewById(R.id.counttime);
        new CountDownTimer(600000,1000) {
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
        txtGhe.setText(ghe);
    }
}
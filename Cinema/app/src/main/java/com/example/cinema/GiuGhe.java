package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class GiuGhe extends AppCompatActivity {
    TextView counttime;
    public int counter=10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giu_ghe);
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
    }
}
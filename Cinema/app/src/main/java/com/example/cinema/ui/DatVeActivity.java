package com.example.cinema.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinema.R;
import com.example.cinema.adapters.MyDatePicker;

public class DatVeActivity extends AppCompatActivity {
    NumberPicker numPicker1=null;  // number pickers to select no of seats
    NumberPicker numPicker2=null;
    NumberPicker numPicker3=null;
    TextView t1, tvDate;
    static int seniors=0, adults=0, infants=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);

        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Đặt vé");

        t1 = (TextView) findViewById(R.id.textViewTime1);
        tvDate = (TextView) findViewById(R.id.textViewDate1);
        numPicker1 = (NumberPicker) findViewById(R.id.numberPicker1);
        numPicker2 = (NumberPicker) findViewById(R.id.numberPicker2);
        numPicker3 = (NumberPicker) findViewById(R.id.numberPicker3);


        Button b = (Button) findViewById(R.id.btnPickDate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();

            }

        });

        numPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal)
            {
                infants=newVal;


            }
        });
        numPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal)
            {
                adults=newVal;
            }
        });
        numPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal)
            {
                seniors=newVal;
            }
        });

        numPicker1.setMaxValue(38);
        numPicker1.setMinValue(0);
        numPicker1.setWrapSelectorWheel(false);
        numPicker2.setMaxValue(38);
        numPicker2.setMinValue(0);
        numPicker2.setWrapSelectorWheel(false);
        numPicker3.setMaxValue(38);
        numPicker3.setMinValue(0);
        numPicker3.setWrapSelectorWheel(false);
    }

    private void showDate() {

        DialogFragment newFragment = new MyDatePicker();


        // sending some data to the Fragment via Bundle
        Bundle args = new Bundle();
        args.putInt("dTheme", 4);
        args.putInt("destination",R.id.textViewDate1);

        newFragment.setArguments(args);

        newFragment.show(getFragmentManager(),"Date Picker");


    }

    public void DatVe(View view) {
        Intent intent= new Intent(getApplicationContext(), ChonGheActivity.class);
//        intent.putExtra("totalTickets", infants+seniors+adults);
        intent.putExtra("title", getIntent().getExtras().getString("title"));
        intent.putExtra("date", tvDate.getText().toString());
        intent.putExtra("infants", infants);
        intent.putExtra("adults", adults);
        intent.putExtra("seniors", seniors);
        startActivity(intent);
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

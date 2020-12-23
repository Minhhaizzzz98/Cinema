package com.example.cinema.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinema.R;

import java.util.ArrayList;

public class ChonGheActivity extends AppCompatActivity {

    Boolean[] btnSelected= new Boolean[40]; // array to store selected or not selected status of each seat
    int noOfSelectedSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);

        // Tao nut Back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Chọn ghế");

        Bundle bundle = getIntent().getExtras();
        final int infants = bundle.getInt("infants");
        final int adults = bundle.getInt("adults");
        final int seniors = bundle.getInt("seniors");

        Toast.makeText(this, infants+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, seniors+"", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, adults+"", Toast.LENGTH_SHORT).show();

        final int totalTickets=infants+adults+seniors;

        Button[] seatButtons = new Button[40];
        final ArrayList<Integer> selectedButtons = new ArrayList<Integer>();
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
        for(int i=0;i<40;i++){
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
            final int buttonNum = i;

            // set listeners to all buttons
            if (seatButtons[i].isEnabled() && noOfSelectedSeats<=totalTickets) {

                seatButtons[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        int btnId = finalId;
                        Button btn = (Button) findViewById(btnId);
                        if (!btnSelected[buttonNum]) {
                            btn.setBackground(getResources().getDrawable(R.drawable.selected));
                            btnSelected[buttonNum] = true;
                            noOfSelectedSeats=noOfSelectedSeats+1;
                            selectedButtons.add(buttonNum);

                        } else {
                            btn.setBackground(getResources().getDrawable(R.drawable.available));
                            btnSelected[buttonNum] = false;
                            noOfSelectedSeats=noOfSelectedSeats-1;

                            try {
                                selectedButtons.remove(buttonNum);
                            }
                            catch(Exception ex)
                            {
                                System.out.println(ex);
                            }
                        }

                    }
                });

            }
// go to Receipt page i.e. Payment Activity after selecting seats and confirming seats

            Button btnProceed = (Button) findViewById(R.id.btnConfirmSeats);
            btnProceed.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {
                    if (noOfSelectedSeats > totalTickets) {
                        Toast.makeText(getApplicationContext(), "Please select > of seats as selected previously", Toast.LENGTH_LONG).show();
                    }
                    else if (noOfSelectedSeats < totalTickets) {
                        Toast.makeText(getApplicationContext(), "Please select < of seats as selected previously", Toast.LENGTH_LONG).show();
                    }
                    else  {

                        for (int i = 0; i < selectedButtons.size(); i++) {
                           Toast.makeText(getApplicationContext(),"Seat no "+selectedButtons.get(i), Toast.LENGTH_SHORT).show();
                        }

                        Intent myintent = new Intent(getApplicationContext(), PaymentActivity.class);
                        myintent.putExtra("selectedSeatsList",selectedButtons);
                        myintent.putExtra("movieTitle", getIntent().getExtras().getString("title"));
//                        myintent.putExtra("movieId",movieId);
//                        myintent.putExtra("movieStartTime",movieStartTime);
                        myintent.putExtra("infants",infants);
                        myintent.putExtra("adults",adults);
                        myintent.putExtra("seniors",seniors);
                        myintent.putExtra("movieDate",getIntent().getExtras().getString("date"));

                        startActivity(myintent);
                    }
                }

            });
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
}
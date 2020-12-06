package com.example.cinema.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cinema.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ChonGheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);

//        Button[] seatButtons = new Button[40];
//        final ArrayList<Integer> selectedButtons = new ArrayList<Integer>();
//
//        // check which seats are booked and show  them as booked to user  for a particular movie ,date and time
//        List<String> bookedSeats = checkBookedSeats(movieId, movieTitle, movieDate, movieStartTime);
//
//        // set listeners to all buttons
//        if (seatButtons[i].isEnabled() && noOfSelectedSeats <= totalTickets) {
//
//            seatButtons[i].setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    int btnId = finalId;
//                    Button btn = (Button) findViewById(btnId);
//                    if (!btnSelected[buttonNum]) {
//                        btn.setBackground(getResources().getDrawable(R.drawable.selected));
//                        btnSelected[buttonNum] = true;
//                        noOfSelectedSeats = noOfSelectedSeats + 1;
//                        selectedButtons.add(buttonNum);
//
//                    } else {
//                        btn.setBackground(getResources().getDrawable(R.drawable.available));
//                        btnSelected[buttonNum] = false;
//                        noOfSelectedSeats = noOfSelectedSeats - 1;
//
//                        try {
//                            selectedButtons.remove(buttonNum);
//                        } catch (Exception ex) {
//                            System.out.println(ex);
//                        }
//                    }
//
//                }
//
//
//            });
//
//        }
    }


}
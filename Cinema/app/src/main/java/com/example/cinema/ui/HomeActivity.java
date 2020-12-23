package com.example.cinema.ui;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cinema.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class HomeActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private static final int MY_NOTIFICATION_ID = 12345;
    private static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        createNotificationChannel();
        toolbar= getSupportActionBar();
        BottomNavigationView bottomNavigation= (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment= new TrangChuFragment();
        loadFragment(fragment);
        toolbar.setTitle(R.string.bottom_navigation_trang_chu);


    }
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.navigation_trangchu:
                    toolbar.setTitle(R.string.bottom_navigation_trang_chu);
                    fragment= new TrangChuFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_rap:
                    toolbar.setTitle(R.string.bottom_navigation_rap_phim);
                    fragment= new rapchieu();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dienanh:
                    toolbar.setTitle(R.string.bottom_navigation_bai_viet);
                    fragment= new DienAnhFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    // Khoi tao Option Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.search:
                toolbar.setTitle("Tìm kiếm");
                fragment= new SearchFragment();
                loadFragment(fragment);
                return true;
            case R.id.notification:
                toolbar.setTitle("Thông báo");
                fragment= new NotificationFragment();
                loadFragment(fragment);
                notibtn();
                return true;
            case R.id.account:
                Intent intent= new Intent(this, Login.class);
                startActivity(intent);
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

    public void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            String name = "Vphuot";
            String descriptionText = "Thong bao vphuot";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel =new NotificationChannel("Channel-001", name, importance);
            mChannel.setDescription(descriptionText);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(mChannel);
        }
    }
    public void notibtn() {
        String title = "ok";

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title", title);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, MY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel-001")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("This is a ticker")
                .setContentTitle("this is title")
                .setContentText("this is content text ... ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationService= (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationService.notify(MY_NOTIFICATION_ID, builder.build());
    }

}

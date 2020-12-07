package com.example.cinema.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cinema.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class HomeActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                    fragment= new TrangChuFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_taikhoan:
                    Intent intent= new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    toolbar.setTitle(R.string.bottom_navigation_tai_khoan);
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
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.search:
//                return true;
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

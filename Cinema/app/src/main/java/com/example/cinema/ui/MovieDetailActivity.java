package com.example.cinema.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cinema.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg;
    private WebView MovieCoverTrailer;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        // ini views
        iniViews();

    }

    void iniViews() {
//        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
//        int imagecover = getIntent().getExtras().getInt("imgCover");
        String trailer= getIntent().getExtras().getString("trailer");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverTrailer = findViewById(R.id.detail_movie_cover);
        MovieCoverTrailer.getSettings().setJavaScriptEnabled(true);
        MovieCoverTrailer.setWebChromeClient(new WebChromeClient());
        MovieCoverTrailer.loadData(trailer, "text/html", "utf-8");

//        Glide.with(this).load(imagecover).into(MovieCoverTrailer);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc);
        // setup animation
//        MovieCoverTrailer.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
//        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

    }


    public void DatVe(View view) {
        Intent intent= new Intent(this, DatVeActivity.class);
        startActivity(intent);
    }
}

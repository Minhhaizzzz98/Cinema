package com.example.cinema.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cinema.R;
import com.example.cinema.XemTrailer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
        play_fab=(FloatingActionButton)findViewById(R.id.play_fab);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieDetailActivity.this, XemTrailer.class);
                startActivity(intent);
            }
        });

    }

    void iniViews() {
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String trailer= getIntent().getExtras().getString("trailer");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Picasso.get().load(imageResourceId).networkPolicy(NetworkPolicy.OFFLINE).into(MovieThumbnailImg);
      //  Glide.with(this).load(imageResourceId).networkPolicy(NetworkPolicy.OFFLINE).into(MovieThumbnailImg);
       // MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverTrailer = findViewById(R.id.detail_movie_cover);
        MovieCoverTrailer.getSettings().setJavaScriptEnabled(true);
        MovieCoverTrailer.setWebChromeClient(new WebChromeClient());
        MovieCoverTrailer.loadData(trailer, "text/html", "utf-8");

        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc);


    }


    public void DatVe(View view) {
        Intent intent= new Intent(this, DatVeActivity.class);
        intent.putExtra("title",getIntent().getExtras().getString("title"));
//        intent.putExtra("imgURL",getIntent().getExtras().getString("imgURL"));
//        intent.putExtra("imgCover",movie.getCoverPhoto());
//        intent.putExtra("trailer", movie.getStreamingLink());
        startActivity(intent);
    }
}

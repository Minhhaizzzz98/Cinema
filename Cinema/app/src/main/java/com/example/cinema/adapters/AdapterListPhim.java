package com.example.cinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterListPhim extends RecyclerView.Adapter<AdapterListPhim.HolderListPhim> {
    Context context ;
    List<Movie> mData;
    ArrayList<Movie> arraylist=new ArrayList<Movie>();
    MovieItemClickListener movieItemClickListener;

    public AdapterListPhim(Context context, List<Movie> mData, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener = movieItemClickListener;
        arraylist.addAll(mData);
    }

    @NonNull
    @Override
    public HolderListPhim onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phim_list,parent,false);
        return new HolderListPhim(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderListPhim holder, int position) {
        holder.TvTitle.setText(mData.get(position).getTitle());
        holder.ImgMovie.setImageResource(mData.get(position).getThumbnail());
        holder.Loai.setText(mData.get(position).getLoaiPhim());
       holder.ratingBar.setRating(5);
       // holder.ratingBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setScaleAnimation(holder.itemView);
    }
    private int lastPosition = -1;
    @Override
    public int getItemCount() {
        return mData.size();
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    public class HolderListPhim extends RecyclerView.ViewHolder {
        private TextView TvTitle;
        private ImageView ImgMovie;
        private TextView Loai;
        private RatingBar ratingBar;

        public HolderListPhim(@NonNull View itemView) {
            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);
            Loai=itemView.findViewById(R.id.item_loai);
            ratingBar=(RatingBar)itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);

                }
            });
        }
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mData.clear();
        if (charText.length() == 0) {
            mData.addAll(arraylist);
        } else {
            for (Movie wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mData.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

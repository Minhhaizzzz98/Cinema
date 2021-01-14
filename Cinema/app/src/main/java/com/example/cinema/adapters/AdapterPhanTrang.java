package com.example.cinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.ui.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhanTrang extends RecyclerView.Adapter<AdapterPhanTrang.ViewHolder> {
    Context context;
    List<Integer> list = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public AdapterPhanTrang(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer integer = list.get(position);
        holder.textView.setText(integer+"");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    Toast.makeText(context, "Long Click: "+integer, Toast.LENGTH_SHORT).show();
                else{
                    switch (integer){
                        case 1: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(0, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 2: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(4, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 3: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(8, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 4: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(12, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 5: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(16, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 6: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(24, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 7: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(32, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 8: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(36, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                        case 9: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                            SearchFragment dienAnhFragment =new SearchFragment(40, integer*4);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, dienAnhFragment).addToBackStack(null).commit();
                            break;
                        }
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textView;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.number);
            itemView.setOnClickListener(this); // set sự kiên onClick cho View
            itemView.setOnLongClickListener(this); // set sự kiên onLongClick cho View

        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}

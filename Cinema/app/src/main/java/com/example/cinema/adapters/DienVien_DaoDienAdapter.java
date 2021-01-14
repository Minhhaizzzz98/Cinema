package com.example.cinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.models.Actor;

import java.util.List;

public class DienVien_DaoDienAdapter extends RecyclerView.Adapter<DienVien_DaoDienAdapter.ViewHolder> {

    Context context;
    List<Actor> list;

    public DienVien_DaoDienAdapter(Context context, List<Actor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DienVien_DaoDienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chi_tiet_phim_dien_vien, parent, false);
        return new DienVien_DaoDienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DienVien_DaoDienAdapter.ViewHolder holder, int position) {
        Actor actor = list.get(position);
        holder.name.setText(actor.getName());
        holder.imageView.setImageResource(actor.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }
    }
}

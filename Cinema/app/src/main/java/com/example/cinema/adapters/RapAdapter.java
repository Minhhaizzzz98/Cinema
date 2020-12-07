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
import com.example.cinema.models.Movie;
import com.example.cinema.models.Rap;

import java.util.List;

public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder> {
    Context context ;
    List<Rap> mData;


    public RapAdapter(Context context, List<Rap> mData) {
        this.context = context;
        this.mData = mData;

    }

    @NonNull
    @Override
    public RapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rap_item,parent,false);
        return new RapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RapViewHolder holder, int position) {
        holder.txtName.setText(mData.get(position).getName());
        holder.txtAddress.setText(mData.get(position).getAddress());
        holder.txtPhone.setText(mData.get(position).getPhone());
        holder.ivRap.setImageResource(mData.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RapViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRap;
        TextView txtName;
        TextView txtAddress;
        TextView txtPhone;
        public RapViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRap=(ImageView)itemView.findViewById(R.id.ivRap);
            txtName=(TextView)itemView.findViewById(R.id.txtRapName);
            txtAddress=(TextView)itemView.findViewById(R.id.txtRapDiaChi);
            txtPhone=(TextView)itemView.findViewById(R.id.txtRapPhone);
        }
    }
}

package com.example.cinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.models.BinhLuan;

import java.util.LinkedList;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanHolder> {
    LinkedList<BinhLuan> mData;
    Context mContext;

    public BinhLuanAdapter(LinkedList<BinhLuan> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BinhLuanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.item_binhluan,parent,false);

        return new BinhLuanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanHolder holder, int position) {
        holder.txtNoiDung.setText(mData.get(position).getNoidung());
        holder.txtTen.setText(mData.get(position).getTenkhachhang());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BinhLuanHolder extends RecyclerView.ViewHolder {
        private TextView txtTen;
        private TextView txtNoiDung;
        public BinhLuanHolder(@NonNull View itemView) {
            super(itemView);
            txtNoiDung=(TextView)itemView.findViewById(R.id.txt_noidung_bl);
            txtTen=(TextView)itemView.findViewById(R.id.txt_ten_kh);
        }
    }
}

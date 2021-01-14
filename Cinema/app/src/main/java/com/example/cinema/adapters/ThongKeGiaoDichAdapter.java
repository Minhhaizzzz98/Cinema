package com.example.cinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.models.GiaoDich;

import java.util.LinkedList;

public class ThongKeGiaoDichAdapter extends RecyclerView.Adapter<ThongKeGiaoDichAdapter.ThongKeGiaoDichViewHolder> {
    LinkedList<GiaoDich> lstGiaoDich;
    LayoutInflater inflater;

    public ThongKeGiaoDichAdapter(LinkedList<GiaoDich> lstGiaoDich, Context context) {
        this.lstGiaoDich = lstGiaoDich;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ThongKeGiaoDichAdapter.ThongKeGiaoDichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_thong_ke_giao_dich_item, parent, false);
        return new ThongKeGiaoDichViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeGiaoDichAdapter.ThongKeGiaoDichViewHolder holder, int position) {
        GiaoDich giaoDich = lstGiaoDich.get(position);

        holder.tvNgayMua.setText(giaoDich.getNgayMua());
        holder.tvSoTien.setText(String.valueOf(giaoDich.getSoTien()));

    }

    @Override
    public int getItemCount() {
        return lstGiaoDich.size();
    }

    public class ThongKeGiaoDichViewHolder extends RecyclerView.ViewHolder {

        TextView tvNgayMua, tvSoTien;
        ThongKeGiaoDichAdapter _adapter;

        public ThongKeGiaoDichViewHolder(@NonNull View itemView, ThongKeGiaoDichAdapter adapter) {
            super(itemView);

            tvNgayMua = itemView.findViewById(R.id.tvThoiGian);
            tvSoTien = itemView.findViewById(R.id.tvSoTien);
            _adapter = adapter;
        }
    }
}

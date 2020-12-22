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

public class LichSuGiaoDichAdapter extends RecyclerView.Adapter<LichSuGiaoDichAdapter.LichSuGiaoDichViewHolder>{
    LinkedList<GiaoDich> lstGiaoDich;
    LayoutInflater inflater;

    public LichSuGiaoDichAdapter(LinkedList<GiaoDich> lstGiaoDich, Context context) {
        this.lstGiaoDich = lstGiaoDich;
        this.inflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LichSuGiaoDichAdapter.LichSuGiaoDichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.layout_lich_su_giao_dich_item, parent, false);
        return new LichSuGiaoDichViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuGiaoDichAdapter.LichSuGiaoDichViewHolder holder, int position) {
        GiaoDich giaoDich= new GiaoDich();

        holder.tvTenPhim.setText( giaoDich.getTenPhim());
        holder.tvNgayMua.setText( giaoDich.getNgayMua());
        holder.tvSoTien.setText( giaoDich.getSoTien());

    }

    @Override
    public int getItemCount() {
        return lstGiaoDich.size();
    }

    public class LichSuGiaoDichViewHolder extends RecyclerView.ViewHolder{

        TextView tvTenPhim, tvNgayMua, tvSoTien;
        LichSuGiaoDichAdapter _adapter;
        public LichSuGiaoDichViewHolder(@NonNull View itemView, LichSuGiaoDichAdapter adapter) {
            super(itemView);

            tvTenPhim= itemView.findViewById(R.id.tvTenPhim);
            tvNgayMua= itemView.findViewById(R.id.tvNgayMua);
            tvSoTien= itemView.findViewById(R.id.tvSoTien);
            _adapter= adapter;
        }
    }
}

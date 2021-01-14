package com.example.cinema.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.models.GioChieu;

import java.util.ArrayList;
import java.util.List;

public class GioChieuAdapter extends RecyclerView.Adapter<GioChieuAdapter.GioChieuHolder> {
    List<GioChieu> mData;
    Context context;
    List<CardView>cardViewList = new ArrayList<>();
    List<TextView> lstNgay=new ArrayList<>();
    GioItemClick lichItemClick;
    public GioChieuAdapter(List<GioChieu> mData, Context context,GioItemClick lichItemClick) {
        this.mData = mData;
        this.context = context;
        this.lichItemClick=lichItemClick;
    }

    @NonNull
    @Override
    public GioChieuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_chon_gio,parent,false);
        return new GioChieuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioChieuHolder holder, int position) {

        holder.txtGio.setText(mData.get(position).getThoigian());
        cardViewList.add(holder.layout_lich);
        lstNgay.add(holder.txtGio);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GioChieuHolder extends RecyclerView.ViewHolder {
        TextView txtGio;
        CardView layout_lich;
        RelativeLayout gio;

        public GioChieuHolder(@NonNull View itemView) {
            super(itemView);
            txtGio=(TextView)itemView.findViewById(R.id.txt_gio_chieu);
            layout_lich=(CardView)itemView.findViewById(R.id.card_gio);
            gio=(RelativeLayout)itemView.findViewById(R.id.bg_gio_chieu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (TextView textView:lstNgay)
                    {
                        textView.setTextColor(context.getResources().getColor(R.color.black));
                    }
                    for(CardView cardView : cardViewList){

                        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                    }
                    layout_lich.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    txtGio.setTextColor(context.getResources().getColor(R.color.white));
                    lichItemClick.onGioClick(mData.get(getAdapterPosition()),gio);
                }
            });
        }
    }
}

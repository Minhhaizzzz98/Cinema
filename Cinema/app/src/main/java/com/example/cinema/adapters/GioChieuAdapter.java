package com.example.cinema.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

import java.util.ArrayList;
import java.util.List;

public class GioChieuAdapter extends RecyclerView.Adapter<GioChieuAdapter.GioChieuHolder> {
    List<String> mData;
    Context context;
    List<CardView>cardViewList = new ArrayList<>();
    List<TextView> lstNgay=new ArrayList<>();
    public GioChieuAdapter(List<String> mData, Context context) {
        this.mData = mData;
        this.context = context;
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

        holder.txtGio.setText(mData.get(position));
        cardViewList.add(holder.layout_lich);
        lstNgay.add(holder.txtGio);
        holder.layout_lich.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                for (TextView textView:lstNgay)
                {
                    textView.setTextColor(context.getResources().getColor(R.color.black));
                }
                for(CardView cardView : cardViewList){

                    cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                }
                holder.layout_lich.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                holder.txtGio.setTextColor(context.getResources().getColor(R.color.white));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GioChieuHolder extends RecyclerView.ViewHolder {
        TextView txtGio;
        CardView layout_lich;
        public GioChieuHolder(@NonNull View itemView) {
            super(itemView);
            txtGio=(TextView)itemView.findViewById(R.id.txt_gio_chieu);
            layout_lich=(CardView)itemView.findViewById(R.id.card_gio);
        }
    }
}

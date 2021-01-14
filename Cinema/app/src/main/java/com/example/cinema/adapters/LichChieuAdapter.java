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
import com.example.cinema.models.Lich;

import java.util.ArrayList;
import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.LichChieuHolder>{
    Context context ;
    List<Lich> mData;
    MovieItemClickListener movieItemClickListener;
    LichItemClick lichItemClick;
    List<CardView>cardViewList = new ArrayList<>();
    List<TextView> lstThu=new ArrayList<>();
    List<TextView> lstNgay=new ArrayList<>();

    public LichChieuAdapter(Context context, List<Lich> mData,LichItemClick movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.lichItemClick = movieItemClickListener;

    }

    @NonNull
    @Override
    public LichChieuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_chon_lich,parent,false);
        return  new LichChieuHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LichChieuHolder holder, int position) {
        holder.txtThu.setText(mData.get(position).getNgay());
        holder.txtNgay.setText(mData.get(position).getThu());
        cardViewList.add(holder.layout_lich);
        lstNgay.add(holder.txtNgay);
        lstThu.add(holder.txtThu);
//        holder.layout_lich.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                for (TextView textView:lstNgay)
//                {
//                    textView.setTextColor(context.getResources().getColor(R.color.colorDefault));
//                }
//                for (TextView textView:lstThu)
//                {
//                    textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//                }
//                for(CardView cardView : cardViewList){
//
//                    cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
//                }
//
//                //The selected card is set to colorSelected
//                holder.layout_lich.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//                holder.txtNgay.setTextColor(context.getResources().getColor(R.color.white));
//                holder.txtThu.setTextColor(context.getResources().getColor(R.color.white));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class LichChieuHolder extends RecyclerView.ViewHolder {
        TextView txtThu;
        TextView txtNgay;
        CardView layout_lich;
        RelativeLayout relativeLayout;
        public LichChieuHolder(@NonNull View itemView) {
            super(itemView);
            txtThu=(TextView)itemView.findViewById(R.id.txt_thu);
            txtNgay=(TextView)itemView.findViewById(R.id.txt_ngay);
            layout_lich=(CardView) itemView.findViewById(R.id.layout_lich);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.layout_lich_bg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (TextView textView:lstNgay)
                    {
                        textView.setTextColor(context.getResources().getColor(R.color.colorDefault));
                    }
                    for (TextView textView:lstThu)
                    {
                        textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    }
                    for(CardView cardView : cardViewList){

                        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                    }

                    //The selected card is set to colorSelected
                    layout_lich.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                    txtNgay.setTextColor(context.getResources().getColor(R.color.white));
                    txtThu.setTextColor(context.getResources().getColor(R.color.white));
                    lichItemClick.onLichClick(mData.get(getAdapterPosition()),relativeLayout);

                }
            });

        }

    }
}

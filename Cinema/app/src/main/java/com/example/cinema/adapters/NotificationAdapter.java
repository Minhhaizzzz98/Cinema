package com.example.cinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.models.Notification;
import com.example.cinema.ui.DienAnhFragment;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewholder> {
    List<Notification> list = new ArrayList<>();
    Context context;

    public NotificationAdapter(List<Notification> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new  viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.viewholder holder, int position) {
        Notification notification= list.get(0);
        holder.title.setText(notification.getTitle());
        holder.content.setText(notification.getContent());
        holder.imageView.setImageResource(notification.getImg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Fragment fragment;
                if(isLongClick)
                    Toast.makeText(context, "Long Click: "+notification, Toast.LENGTH_SHORT).show();
                else{
                    switch (notification.getStatus()){
                        case 1: {
                            AppCompatActivity activity = (AppCompatActivity) view.getContext();
                             DienAnhFragment dienAnhFragment =new DienAnhFragment();
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

    public class viewholder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{
        ImageView imageView;
        TextView title;
        TextView content;
        private ItemClickListener itemClickListener;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imv_thongbao);
            title = itemView.findViewById(R.id.tvTenThongBao);
            content = itemView.findViewById(R.id.tvNoiDungThongBao);
            itemView.setOnClickListener(this); // set sự kiên onClick cho View
            itemView.setOnLongClickListener(this); // set sự kiên onLongClick cho View
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}

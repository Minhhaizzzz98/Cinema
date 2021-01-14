package com.example.cinema.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cinema.models.Rap;

import java.util.LinkedList;

public class SpinnerRapAdapter extends ArrayAdapter<Rap> {
    private Context context;
    // Your custom values for the spinner (User)
    private LinkedList<Rap> mData;
    public SpinnerRapAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public Rap getItem(int position) {
        return mData.get(position);
    }



    public SpinnerRapAdapter(@NonNull Context context, int resource, @NonNull LinkedList<Rap>objects) {
        super(context, resource, objects);
        this.context=context;
        this.mData=objects;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(mData.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(mData.get(position).getName());
        return label;
    }
}

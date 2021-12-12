package com.example.lab1ofmobiledevelopment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FoodHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView quantity;
    TextView label;
    TextView category;
    public FoodHolder(View view) {
        super(view);
        quantity=(TextView)view.findViewById(R.id.Quantiy);
        label = (TextView) view.findViewById(R.id.label);
        category = (TextView) view.findViewById(R.id.category);


    }

}
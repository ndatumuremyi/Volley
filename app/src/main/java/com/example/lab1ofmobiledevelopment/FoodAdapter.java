package com.example.lab1ofmobiledevelopment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

    public class FoodAdapter extends RecyclerView.Adapter<FoodHolder> {
        private ArrayList<Food> FoodList;
        View view;
        String type;


        View.OnClickListener listener;

        public FoodAdapter(ArrayList<Food> FoodList){
            this.FoodList=FoodList;
        }
        public FoodAdapter(ArrayList<Food> FoodList, View.OnClickListener listener){
            this.FoodList=FoodList;
            this.listener = listener;
        }
        public FoodAdapter(ArrayList<Food> FoodList, View.OnClickListener listener, String type){
            this.FoodList=FoodList;
            this.listener = listener;
            this.type = type;
        }

        @Override
        public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food ,parent,false);
            view.setOnClickListener(listener);
            return new FoodHolder(view);
        }

        @Override
        public void onBindViewHolder(FoodHolder holder, int position) {
            Food Food=FoodList.get(position);
            holder.quantity.setText(String.valueOf(Food.getQuantity()));
            holder.category.setText(Food.getCategory());
            holder.label.setText(Food.getLabel());
            view.setTag(Food);

        }

        @Override
        public int getItemCount() {
            return FoodList.size();
        }
    }

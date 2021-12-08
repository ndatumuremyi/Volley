package com.example.lab1ofmobiledevelopment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryHolder> {
    private List<Memory> MemoryList;
    View view;

    View.OnClickListener listener;

    public MemoryAdapter(List<Memory> MemoryList){
        this.MemoryList=MemoryList;
    }
    public MemoryAdapter(List<Memory> MemoryList,View.OnClickListener listener){
        this.MemoryList=MemoryList;
        this.listener = listener;
    }

    @Override
    public MemoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_row ,parent,false);
        view.setOnClickListener(listener);
        return new MemoryHolder(view);
    }

    @Override
    public void onBindViewHolder(MemoryHolder holder, int position) {
        Memory memory=MemoryList.get(position);
        holder.provinceName.setText(memory.getProvince().getName());
        holder.memory.setText(memory.getMemory());
        view.setTag(memory);

    }

    @Override
    public int getItemCount() {
        return MemoryList.size();
    }
}

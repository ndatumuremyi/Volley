package com.example.lab1ofmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MemoriesActivity extends AppCompatActivity {
    DatabaseHandler dbHandler;
    ArrayList<Food> foods;
    RecyclerView recyclerView;
    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);


        dbHandler = new DatabaseHandler(this);
        foods=dbHandler.getAllFood();
        foodAdapter = new FoodAdapter(foods, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MemoriesActivity.this, "update", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MemoriesActivity.this, UpdateActivity.class);
                Food selectedFood =(Food) view.getTag();

                intent.putExtra("selectedFood", selectedFood);
                startActivity(intent);
            }
        }, "display");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCont);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(foodAdapter);


        Button button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
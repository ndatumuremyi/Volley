package com.example.lab1ofmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = getIntent();

        Food food =(Food) intent.getSerializableExtra("selectedFood");

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        Button send = findViewById(R.id.updateB);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText q = findViewById(R.id.quantityField);

                food.setQuantity(Integer.parseInt(q.getText().toString()));
               dbHandler.addFood(food);

               Intent intent1 = new Intent(AddActivity.this, MemoriesActivity.class);
               startActivity(intent1);
            }
        });
    }



}
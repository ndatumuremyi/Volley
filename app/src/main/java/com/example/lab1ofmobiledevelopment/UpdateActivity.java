package com.example.lab1ofmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
         food =(Food) intent.getSerializableExtra("selectedFood");

        EditText qty = findViewById(R.id.quantityField);
        qty.setText(String.valueOf(food.getQuantity()));
        EditText label = findViewById(R.id.labelEdit);
        EditText category = findViewById(R.id.categoryEdit);

        label.setText(food.getLabel());
        category.setText(food.getCategory());

        Button delete = findViewById(R.id.deleteB);
        Button update = findViewById(R.id.updateB);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteFood(food);
                Intent intent1 = new Intent(UpdateActivity.this, MemoriesActivity.class);
                startActivity(intent1);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food.setQuantity(Integer.parseInt(qty.getText().toString()));
                food.setCategory(category.getText().toString());
                food.setLabel(label.getText().toString());
                dbHandler.updateFood(food);

                Intent intent1 = new Intent(UpdateActivity.this, MemoriesActivity.class);
                startActivity(intent1);
            }
        });

    }
}
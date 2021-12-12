package com.example.lab1ofmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener listener =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Food selectedFood =(Food) view.getTag();
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.putExtra("selectedFood",selectedFood);
            startActivity(intent);
        }
    };
    RecyclerView recyclerView;
    FoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        getData();

        Button seeMemories = findViewById(R.id.seeMemories);
        seeMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MemoriesActivity.class);
                startActivity(intent);
            }
        });
    }
    public void displayProvince(){
        Log.d("TEST","DADADADADAD");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
    public void getData(){
        Log.d("TAG", "getData");
        ArrayList<Food> foods = new ArrayList<>();

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://edamam-food-and-grocery-database.p.rapidapi.com/parser?ingr=apple";
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE",response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("hints");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject foodE = jsonArray.getJSONObject(i).getJSONObject("food");
                                Log.d("RESPONSE", "onResponse: " + foodE);
//                                String prov=jsonArray.getString(i);
//                                Province province=new Province(prov);
                                Food food = new Food();

                                food.setCategory(foodE.getString("category"));
                                food.setLabel(foodE.getString("label"));
                                foods.add(food);
                                food.print();
                            }
                            adapter = new FoodAdapter(foods, listener);
                            displayProvince();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR",error.toString());
            }

        }){
            @Override
            public Map<String,String> getHeaders(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("x-rapidapi-host", "edamam-food-and-grocery-database.p.rapidapi.com");
                params.put("x-rapidapi-key", "6b1d2a7879msh375f16e28cd9056p1d8f4bjsn415a8c9e4a27");

                return params;
            }
        };
        requestQueue.add(request);
    }
}
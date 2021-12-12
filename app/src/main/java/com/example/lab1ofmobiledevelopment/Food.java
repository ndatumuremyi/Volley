package com.example.lab1ofmobiledevelopment;

import android.util.Log;

import java.io.Serializable;

public class Food implements Serializable {
    int foodId;
    String label;
    String category;
    int quantity = 0;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food(int foodId, String label, String category) {
        this.foodId = foodId;
        this.label = label;
        this.category = category;
    }

    public Food() {
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void print(){
        Log.d("Tag",getCategory() +" "+ getFoodId()+" "+getFoodId());
    }
}

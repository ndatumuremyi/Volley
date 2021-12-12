package com.example.lab1ofmobiledevelopment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foodManager";
    private static final String TABLE_FOODS = "foods";
    private static final String KEY_ID = "id";
    private static final String KEY_LABEL = "laber";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_quantity = "quantity";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOODS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LABEL + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_quantity + " INTEGER"
                + ")";
        db.execSQL(CREATE_FOOD_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new Food
    long addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LABEL, food.getLabel()); // Contact Name
        values.put(KEY_CATEGORY, food.getCategory()); // Contact Name
        values.put(KEY_quantity, food.getQuantity());

        // Inserting Row
        long d= db.insert(TABLE_FOODS, null, values);
        //2nd argument is String containing nullColumnHack

        db.close(); // Closing database connection
        return d;
    }

    // code to get the single contact
    Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOODS, new String[] { KEY_ID,
                        KEY_LABEL, KEY_CATEGORY, KEY_quantity}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int mid=cursor.getInt(0);
        String label=cursor.getString(1);
        String category=cursor.getString(2);
        int quantity = cursor.getInt(3);
        Food food = new Food(mid,label, category);
        food.setQuantity(quantity);
        // return contact
        return food;
    }

    // code to get all contacts in a list view
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foodList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setFoodId(Integer.parseInt(cursor.getString(0)));
                food.setLabel(cursor.getString(1));
                food.setCategory(cursor.getString(2));
                food.setQuantity(cursor.getInt(3));
                // Adding food to list
                foodList.add(food);
//                Log.d("Data: ",food.getFirstname()+food.getLastname()+food.getTelephone());
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }

    // code to update the single contact
    public int updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LABEL, food.getLabel());
        values.put(KEY_CATEGORY, food.getCategory());
        values.put(KEY_quantity,food.getQuantity());

        // updating row
        return db.update(TABLE_FOODS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(food.getFoodId()) });
    }

    // Deleting single contact
    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOODS, KEY_ID + " = ?",
                new String[] { String.valueOf(food.getFoodId()) });
        db.close();
    }

    // Getting contacts Count
    public int getMemoriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FOODS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
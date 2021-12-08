package com.example.lab1ofmobiledevelopment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "memoryManager";
    private static final String TABLE_MEMORIES = "memories";
    private static final String KEY_ID = "id";
    private static final String KEY_MEMORY = "memory";
    private static final String KEY_PROVINCE = "province";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMORY_TABLE = "CREATE TABLE " + TABLE_MEMORIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_MEMORY + " TEXT,"
                + KEY_PROVINCE + " TEXT"
                  + ")";
        db.execSQL(CREATE_MEMORY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMORIES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new memory
    long addMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MEMORY, memory.getMemory()); // Contact Name
        values.put(KEY_PROVINCE, memory.getProvince().getName()); // Contact Name

        // Inserting Row
        long d= db.insert(TABLE_MEMORIES, null, values);
        //2nd argument is String containing nullColumnHack

        db.close(); // Closing database connection
        return d;
    }

    // code to get the single contact
    Memory getMemory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEMORIES, new String[] { KEY_ID,
                        KEY_MEMORY, KEY_MEMORY }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int mid=cursor.getInt(0);
        String memory_m=cursor.getString(1);
        String province=cursor.getString(2);
        Memory memory = new Memory(mid,memory_m, province);
        // return contact
        return memory;
    }

    // code to get all contacts in a list view
    public List<Memory> getAllMemory() {
        List<Memory> memoryList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEMORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Memory memory = new Memory();
                memory.setId(Integer.parseInt(cursor.getString(0)));
                memory.setMemory(cursor.getString(1));
                memory.setProvince(new Province( cursor.getString(2)));
                // Adding memory to list
                memoryList.add(memory);
//                Log.d("Data: ",memory.getFirstname()+memory.getLastname()+memory.getTelephone());
            } while (cursor.moveToNext());
        }

        // return contact list
        return memoryList;
    }

    // code to update the single contact
    public int updateMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MEMORY, memory.getMemory());
        values.put(KEY_PROVINCE, memory.getProvince().getName());

        // updating row
        return db.update(TABLE_MEMORIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(memory.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(memory.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getMemoriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEMORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
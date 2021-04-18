package com.example.shoplist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Sqllhelper extends SQLiteOpenHelper{
    private Context context;
    private static final String DATABASE_NAME = "Shoplist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_slist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ITEM = "item_name";
    private static final String COLUMN_AMOUNT = "item_amount";
    private static final String COLUMN_PRICE = "item_price";

    Sqllhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addItem(String item, String amount, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM, item);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_PRICE, price);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Oops, fail!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Item added!",Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAll(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    void deleteSolo(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show();
        }
    }

}

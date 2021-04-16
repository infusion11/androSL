package com.example.shoplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Sqllhelper myDB;
    ArrayList<String> item_id, item_name, item_amount, item_price;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new Sqllhelper(MainActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_amount = new ArrayList<>();
        item_price = new ArrayList<>();

        dbtoArray();
        itemsAdapter = new ItemsAdapter(MainActivity.this,item_id,item_name,item_amount,item_price);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }



    void dbtoArray(){
        Cursor cursor = myDB.readAll();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Your list is empty.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_amount.add(cursor.getString(2));
                item_price.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.del_all){
            Sqllhelper myDB = new Sqllhelper(this);
            myDB.deleteAll();
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }
}
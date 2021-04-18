package com.example.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DelActivity extends AppCompatActivity {

    EditText name_input;
    Button del_button;
    String id,name,amount,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);

        name_input = findViewById(R.id.name_input2);
        del_button = findViewById(R.id.del_button);
        getIData();
        del_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqllhelper db = new Sqllhelper(DelActivity.this);
                db.deleteSolo(id);
                finish();
            }
        });

    }

    void getIData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("amount") && getIntent().hasExtra("price")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            name_input.setText(name);

        }else{
            Toast.makeText(this, "Nothing to show.", Toast.LENGTH_SHORT).show();
        }
    }
}
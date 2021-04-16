package com.example.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input, amount_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        amount_input = findViewById(R.id.amount_input);
        price_input = findViewById(R.id.price_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqllhelper myDB = new Sqllhelper(AddActivity.this);
                myDB.addItem(name_input.getText().toString().trim(),
                        amount_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()));
            }
        });
    }
}
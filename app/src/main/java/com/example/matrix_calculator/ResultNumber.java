package com.example.matrix_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_number);

        Intent mIntent = getIntent();
        String name = mIntent.getStringExtra("Name");
        float determinant = mIntent.getFloatExtra("determinant", 0);
        TextView result_TV = findViewById(R.id.Name);
        TextView Number = findViewById(R.id.Number);
        result_TV.setText(name);
        Number.setText(String.valueOf(determinant));

    }
}
package com.example.matrix_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultMatrix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_matrix);

        Intent mIntent = getIntent();
        int rows = mIntent.getIntExtra("rows", 0);
        int columns = mIntent.getIntExtra("columns", 0);
        int[][] result = (int[][]) mIntent.getSerializableExtra("Result");
        TextView[][] result_TV = new TextView[rows][columns];

               // Считываем TextView
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String s = "ET" + i + j;
                result_TV[i][j] = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
            }
        }

        // Нужные TextView visible,  ненужные - gone
        for (int i = 0; i < rows ; i++){
            for (int j = 0; j < columns; j++){
                String s = "ET" + i + j;
                TextView zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.VISIBLE);
            }
        }
        for (int i = rows; i < 6 ; i++){
            for (int j = 0; j < 6; j++){
                String s = "ET" + i + j;
                TextView zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.GONE);
            }
        }
        for (int i = 0; i < 6; i++){
            for (int j = columns; j < 6; j++){
                String s = "ET" + i + j;
                TextView zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.GONE);
            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                result_TV[i][j].setText(String.valueOf(result[i][j]));
            }
        }
    }
}
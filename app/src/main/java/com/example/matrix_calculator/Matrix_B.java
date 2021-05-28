package com.example.matrix_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class Matrix_B extends AppCompatActivity {

    int rowsB = 6;
    int columnsB = 6;

    public void openResult(Context context, int[][] Array, int rows, int columns){
        Intent mIntent = new Intent(context, ResultMatrix.class);
        mIntent.putExtra("Result", Array);
        mIntent.putExtra("rows", rows);
        mIntent.putExtra("columns", columns);
        context.startActivity(mIntent);
    }

    @SuppressLint("SetTextI18n")
    public void matrix_changes(TextView tv, String name, int number){

        tv.setText(name + number);


        for (int i = 0; i < this.rowsB; i++){
            for (int j = 0; j < this.columnsB; j++){
                String s = "ET" + i + j;
                EditText zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.VISIBLE);
            }
        }
        for (int i = this.rowsB; i < 6 ; i++){
            for (int j = 0; j < 6; j++){
                String s = "ET" + i + j;
                EditText zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < 6; i++){
            for (int j = this.columnsB; j < 6; j++){
                String s = "ET" + i + j;
                EditText zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.GONE);
            }
        }
    }

    private int[][] input() {

        int[][] matrix_0 = new int[rowsB][columnsB];
        EditText[][] input_matrices = new EditText[rowsB][columnsB];

        // Считываем EditText - ы
        for (int i = 0; i < this.rowsB; i++) {
            for (int j = 0; j < this.columnsB; j++) {
                String s = "ET" + i + j;
                input_matrices[i][j] = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
            }
        }

        // Заполняем матрицу из input_matrices в matrix_0 и matrix_1
        for (int i = 0; i < this.rowsB; i++) {
            for (int j = 0; j < this.columnsB; j++) {
                if (!input_matrices[i][j].getText().toString().equals("")){
                    matrix_0[i][j] = Integer.parseInt(input_matrices[i][j].getText().toString());
                }

                else{
                    matrix_0[i][j] = 0;
                }
            }
        }
        return matrix_0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_b);

        final TextView TV_Rows = findViewById(R.id.TV_Rows);
        final TextView TV_Columns = findViewById(R.id.TV_Columns);
        final SeekBar Matrix_row = findViewById(R.id.seekBar_row);
        final SeekBar Matrix_column = findViewById(R.id.seekBar_column);
        final Button btn_plus = findViewById(R.id.btn_plus);
        final Button btn_minus = findViewById(R.id.btn_minus);
        final Button btn_multiply = findViewById(R.id.btn_multiply);

        Intent mIntent = getIntent();
        final int rowsA = mIntent.getIntExtra("rowsA", 0);
        final int columnsA = mIntent.getIntExtra("columnsA", 0);
        final int[][] matrix_a = (int[][]) mIntent.getSerializableExtra("Result");

        Matrix_row.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rowsB = Matrix_row.getProgress();
                matrix_changes(TV_Rows, "Rows: ", rowsB);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Matrix_column.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                columnsB = Matrix_column.getProgress();
                matrix_changes(TV_Columns, "Columns: ", columnsB);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[][] matrix_b = input();
                if (rowsA == rowsB && columnsA == columnsB){
                    int[][] result = new int[rowsB][columnsB];

                    for (int i = 0; i < rowsB; i++){
                        for (int j = 0; j < columnsB; j++){
                            result[i][j] = matrix_a[i][j] + matrix_b[i][j];
                        }
                    }

                    openResult(Matrix_B.this, result, rowsB, columnsB);
                }

                else {
                    @SuppressLint("ShowToast") Toast toast = Toast.makeText(Matrix_B.this, "Matrix dimensions doesn't match", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[][] matrix_b = input();
                if (rowsA == rowsB && columnsA == columnsB){
                    int[][] result = new int[rowsB][columnsB];

                    for (int i = 0; i < rowsB; i++){
                        for (int j = 0; j < columnsB; j++){
                            result[i][j] = matrix_a[i][j] - matrix_b[i][j];
                        }
                    }

                    openResult(Matrix_B.this, result, rowsB, columnsB);
                }

                else {
                    @SuppressLint("ShowToast") Toast toast = Toast.makeText(Matrix_B.this, "Matrix dimensions doesn't match", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (columnsA == rowsB){

                    int [][] matrix_b = input();

                    int[][] result = new int[rowsA][columnsB];

                    for(int i = 0; i < rowsA; i++){
                        for(int j = 0; j < columnsB; j++){
                            result[i][j] = 0;
                        }
                    }

                    //  i, j - индексы result[][]
                    for(int i = 0; i < rowsA; i++){
                        for(int j = 0; j < columnsB; j++){
                            for(int k = 0; k < columnsA; k++){  //  проход по строке у матрицы A и по столбца матрицы B
                                result[i][j] += matrix_a[i][k] * matrix_b[k][j];
                            }
                        }
                    }

                    openResult(Matrix_B.this, result, rowsA, columnsB);
                }

                else{
                    Toast.makeText(Matrix_B.this, "Matrices are incompatible", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
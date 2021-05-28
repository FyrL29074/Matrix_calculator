package com.example.matrix_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class Matrix_A extends AppCompatActivity {

    int rowsA = 6;
    int columnsA = 6;


    @SuppressLint("SetTextI18n")
    public void matrix_changes(TextView tv, String name, int number){
        Log.i("TAG", "CRASHa NET");
        tv.setText(name + number + "");
        Log.i("TAG", "CRASH");
        for (int i = 0; i < this.rowsA; i++){
            for (int j = 0; j < this.columnsA; j++){
                String s = "ET" + i + j;
                EditText zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.VISIBLE);
            }
        }
        for (int i = this.rowsA; i < 6 ; i++){
            for (int j = 0; j < 6; j++){
                String s = "ET" + i + j;
                EditText zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < 6; i++){
            for (int j = this.columnsA; j < 6; j++){
                String s = "ET" + i + j;
                EditText zxc = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
                zxc.setVisibility(View.GONE);
            }
        }
    }

    private int[][] input() {

        int[][] matrix_0 = new int[rowsA][columnsA];
        EditText[][] input_matrices = new EditText[rowsA][columnsA];

        // Считываем EditText - ы
        for (int i = 0; i < this.rowsA; i++) {
            for (int j = 0; j < this.columnsA; j++) {
                String s = "ET" + i + j;
                input_matrices[i][j] = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
            }
        }

        // Заполняем матрицу из input_matrices в matrix_0
        for (int i = 0; i < this.rowsA; i++) {
            for (int j = 0; j < this.columnsA; j++) {
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
        setContentView(R.layout.activity_matrix_a);

        final TextView TV_Rows = findViewById(R.id.TV_Rows);
        final TextView TV_Columns = findViewById(R.id.TV_Columns);
        final SeekBar Matrix_row = findViewById(R.id.seekBar_row);
        final SeekBar Matrix_column = findViewById(R.id.seekBar_column);
        final Button btn_transpose = findViewById(R.id.btn_transpose);
        final Button btn_next_matrix = findViewById(R.id.next_matrix);
        final Button btn_determinant = findViewById(R.id.determinant);

        Matrix_row.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rowsA = Matrix_row.getProgress();
                matrix_changes(TV_Rows, "Rows: ", rowsA);
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
                columnsA = Matrix_column.getProgress();
                matrix_changes(TV_Columns, "Columns: ", columnsA);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btn_transpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int [][] result = new int[columnsA][rowsA];
                int [][] matrixHelp = input();
                for(int i = 0; i < rowsA; i++){
                    for(int j = 0; j < columnsA; j++){
                        result[j][i] = matrixHelp[i][j];
                    }
                }

                Intent mIntent = new Intent(Matrix_A.this, ResultMatrix.class);
                mIntent.putExtra("Result", result);
                mIntent.putExtra("rows", columnsA);
                mIntent.putExtra("columns", rowsA);
                startActivity(mIntent);
            }
        });

        btn_determinant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowsA == columnsA){
                    int [][] matrix = input();
                    float[][] matrixFloat = new float[rowsA][columnsA];
                    float determinant = 1;

                    for(int i = 0; i < rowsA; i ++){
                        for(int j = 0; j < columnsA; j++){
                            matrixFloat[i][j] = matrix[i][j];
                        }
                    }

                    //  Приводим матрицу к треугольному виду
                    //  i - Номер строки, относительно которой мы все меняем,  j - номер строки, которую мы меняем,  k - номер столбца элемента, которого мы меняем
                    for(int i = 0; i < rowsA - 1; i++){
                        for(int j = i + 1; j < rowsA; j++){
                            float r = -matrixFloat[j][i] / matrixFloat[i][i];
                            for(int k = i; k < columnsA; k ++){
                                matrixFloat[j][k] += r * matrixFloat[i][k];
                            }
                        }
                    }

                    for(int i = 0; i < rowsA; i++){
                        determinant *= matrixFloat[i][i];
                    }
                    String name = "Determinant";
                    Intent mIntent = new Intent(Matrix_A.this, ResultNumber.class);
                    mIntent.putExtra("determinant", determinant);
                    mIntent.putExtra("Name", name);
                    startActivity(mIntent);

                }

                else {
                    Toast.makeText(Matrix_A.this, "A non-square matrix is specified! (Determinant can't be found)", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_next_matrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[][] matrix0 = input();
                Intent mIntent = new Intent(Matrix_A.this, Matrix_B.class);
                mIntent.putExtra("Result", matrix0);
                mIntent.putExtra("rowsA", rowsA);
                mIntent.putExtra("columnsA", columnsA);
                startActivity(mIntent);
            }
        });
    }
}
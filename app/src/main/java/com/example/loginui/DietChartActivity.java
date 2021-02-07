package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Scanner;

public class DietChartActivity extends AppCompatActivity {

    EditText weight, height;
    TextView result, type;
    LinearLayout home;
    ProgressBar progressBar;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_chart);

        weight = findViewById(R.id.weightEditText);
        height = findViewById(R.id.heightEditText);
        result = findViewById(R.id.textResult);
        type = findViewById(R.id.resultType);
        calculate = findViewById(R.id.calculateBtn);
        home = findViewById(R.id.goToHome);
        progressBar = findViewById(R.id.progressBar);

        // Home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intHome = new Intent(DietChartActivity.this, HomePage.class);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intHome);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              calculateBMI();
            }
        });
    }

    private void calculateBMI () {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr) && weightStr != null & !"".equals(weightStr)){
            float heightValue = Float.parseFloat(heightStr);
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi){

        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0){
            bmiLabel = "Which is very severely underweight";
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0){
            bmiLabel = "Which is severely underweight";
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0){
            bmiLabel = "Which is Underweight";
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <=0){
            bmiLabel = "Which is Normal";
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <=0){
            bmiLabel = "Which is Overweight";
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <=0){
            bmiLabel = "Which is Obese Class I";
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <=0){
            bmiLabel = "Which is Obese Class II";
        } else {
            bmiLabel = "Which is Obese Class III";
        }


        String resultValue = result.getText().toString();
        result.setText(Float.toString(bmi));

        String typeStr = type.getText().toString();
        type.setText(bmiLabel);

    }
}
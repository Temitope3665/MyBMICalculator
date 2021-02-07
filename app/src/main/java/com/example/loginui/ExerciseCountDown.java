package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExerciseCountDown extends AppCompatActivity {

    LinearLayout homeOption;
    ProgressBar progressBar;
    ImageView back;
    Button enter, start, reset, stop;
    EditText exerciseType;
    TextView textView, countdownTimer;
    boolean running;
    int seconds = 0;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_count_down);


        homeOption = findViewById(R.id.goToHome);
        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.backOption);
        exerciseType = findViewById(R.id.exerciseType);
        enter = findViewById(R.id.enterBtn);
        textView = findViewById(R.id.textView);
        start = findViewById(R.id.startBtn);
        reset = findViewById(R.id.resetBtn);
        stop = findViewById(R.id.stopBtn);
        countdownTimer = findViewById(R.id.textTimeView);
        
        runTime();

        // Countdown

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = exerciseType.getText().toString();

                textView.setText(String.format(text));

                if (text.isEmpty()){
                    exerciseType.setError("Kindly input the type of exercise");
                    exerciseType.requestFocus();
                }
                else {
                    running = true;
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = exerciseType.getText().toString();

                textView.setText(String.format(text));

                if (text.isEmpty()){
                    exerciseType.setError("Kindly input the type of exercise");
                    exerciseType.requestFocus();
                }
                else {
                    running = false;
                    seconds = 0;
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = exerciseType.getText().toString();

                textView.setText(String.format(text));

                if (text.isEmpty()){
                    exerciseType.setError("Kindly input the type of exercise");
                    exerciseType.requestFocus();
                }
                else {
                    running = false;
                }

            }
        });


        // Home Option
        homeOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExerciseCountDown.this, HomePage.class));
                //progress bar
                progressBar.setVisibility(View.VISIBLE);
                finish();
            }
        });

        // Back option
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (ExerciseCountDown.this, HomePage.class));
                finish();
            }
        });

        // Enter Option

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = exerciseType.getText().toString();

                textView.setText(String.format(text));

                if (text.isEmpty()){
                    exerciseType.setError("Kindly input the type of exercise");
                    exerciseType.requestFocus();
                }
                else {
                    System.out.println(exerciseType);
                }
            }
        });

    }

    private void runTime() {

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int minute = seconds / 60;
                int mainSeconds = seconds % 60;

                String timer = String.format("%d:%02d:%02d", hour, minute, mainSeconds);
                countdownTimer.setText(timer);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }
}

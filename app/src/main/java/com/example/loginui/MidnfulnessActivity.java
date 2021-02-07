package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MidnfulnessActivity extends AppCompatActivity {

    Button start, reset, stop;
    TextView countdownTimer;
    LinearLayout home;
    ProgressBar progressBar;
    boolean running;
    int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midnfulness);

        start = findViewById(R.id.startBtn);
        reset = findViewById(R.id.resetBtn);
        stop = findViewById(R.id.stopBtn);
        home = findViewById(R.id.goToHome);
        progressBar = findViewById(R.id.progressBar);
        countdownTimer = findViewById(R.id.timer);


        runtime();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                seconds = 0;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
            }
        });
    }

    private void runtime() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minute = seconds / 60;
                int mainSeconds = seconds % 60;

                @SuppressLint("DefaultLocale") String timerr = String.format("%d:%02d:%02d", hours, minute, mainSeconds);
                countdownTimer.setText(timerr);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

        // Home Option
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MidnfulnessActivity.this, HomePage.class));
                //progress bar
                progressBar.setVisibility(View.VISIBLE);
                finish();
            }
        });
    }
}

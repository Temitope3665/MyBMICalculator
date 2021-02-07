package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MeditationActivity extends AppCompatActivity {

    FrameLayout mindfulness;
    FrameLayout spiritual;
    FrameLayout focused;
    FrameLayout movement;
    FrameLayout transcendental;
    LinearLayout home;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        mindfulness = findViewById(R.id.mindfulFrame);
        spiritual = findViewById(R.id.spiritualFrame);
        focused = findViewById(R.id.focusedFrame);
        movement = findViewById(R.id.movementFrame);
        transcendental = findViewById(R.id.transcendentalFrame);
        home = findViewById(R.id.goToHome);
        progressBar = findViewById(R.id.progressBar);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intHome = new Intent (MeditationActivity.this, HomePage.class);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intHome);
            }
        });

        mindfulness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intMind = new Intent(MeditationActivity.this, MidnfulnessActivity.class);
                startActivity(intMind);
            }
        });

        spiritual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSpirit = new Intent(MeditationActivity.this, SpiritualActivity.class);
                startActivity(intSpirit);
            }
        });

        focused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intFocus = new Intent(MeditationActivity.this, FocusedActivity.class);
                startActivity(intFocus);
            }
        });

        movement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intMove = new Intent(MeditationActivity.this, MovementActivity.class);
                startActivity(intMove);
            }
        });

        transcendental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intTrans = new Intent(MeditationActivity.this, TranscendentalActivity.class);
                startActivity(intTrans);
            }
        });
    }
}

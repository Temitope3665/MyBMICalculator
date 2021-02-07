package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class YogaActivity extends AppCompatActivity {

    TextView home;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        home = findViewById(R.id.goHomeText);
        progressBar = findViewById(R.id.progressBar);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intHome = new Intent(YogaActivity.this, HomePage.class);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intHome);
            }
        });

    }
}

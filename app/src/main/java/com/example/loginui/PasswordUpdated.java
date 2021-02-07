package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class PasswordUpdated extends AppCompatActivity {

    Button login;
    ProgressBar progressBar;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_updated);

        mFirebaseAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intGotoLogin = new Intent (PasswordUpdated.this, MainActivity.class);
                startActivity(intGotoLogin);
                // ProgressBar
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}

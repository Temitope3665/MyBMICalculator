package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class first_page_forget_password extends AppCompatActivity {

    EditText emailId;
    Button sendEmailId;
    ImageView back;
    ProgressBar progressBar;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page_forget_password);




        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.enterEmail);
        sendEmailId = findViewById(R.id.sendEmailBtn);
        back = findViewById(R.id.backOption);
        progressBar = findViewById(R.id.progressBar);



        sendEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseAuth.sendPasswordResetEmail(emailId.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // progress bar
                        progressBar.setVisibility(View.VISIBLE);

                        if (task.isSuccessful()) {
                            Toast.makeText(first_page_forget_password.this, "Password sent to email", Toast.LENGTH_LONG).show();
                            // progress bar
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent (first_page_forget_password.this, PasswordUpdated.class);
                            startActivity(intent);
                            // progress bar
                            progressBar.setVisibility(View.VISIBLE);

                        }


                        else {
                            if (!task.isSuccessful()) {
                                // progress bar
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(first_page_forget_password.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (first_page_forget_password.this, MainActivity.class));
                finish();
            }
        });

    }

}

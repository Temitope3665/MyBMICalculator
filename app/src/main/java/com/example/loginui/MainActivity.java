package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.logging.LogRecord;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +             // at least 1 digit
                    "(?=.*[a-z])" +             // at least 1 lower case letter
                    "(?=.*[A-Z])" +             // at least 1 upper case letter
                    "(?=\\S+$)" +               // no white spaces
                    ".{6,12}" +                 // at least 6 characters and less than 12
                    "$");

    EditText userName, pin;
    Button login, signUp;
    TextView forgetPassword;
    CheckBox showPassword;
    ProgressBar progressBar;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.editEmail);
        pin = findViewById(R.id.editPassword);
        login = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.createAccount);
        forgetPassword = findViewById(R.id.textForgetPassword);
        progressBar = findViewById(R.id.progressBar);
        showPassword = findViewById(R.id.showTextPassword);

        mFireBaseAuth = FirebaseAuth.getInstance();




        //showPassword
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    pin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //showPassword End


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userName.getText().toString();
                String password = pin.toString().toString();


                mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                        if (mFirebaseUser != null){
                            Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (MainActivity.this, HomePage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                mFireBaseAuth.addAuthStateListener(mAuthStateListener);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userName.getText().toString();
                String password = pin.getText().toString();


                if (email.isEmpty()){
                    userName.setError("Kindly input your email");
                    userName.requestFocus();
                }

                // Email Validation
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userName.setError("Please enter a valid email address");
                    userName.requestFocus();
                }
                else {
                    userName.setError(null);
                    userName.requestFocus();
                }

                if (password.isEmpty()){
                    pin.setError("Password is required");
                    pin.requestFocus();
                }

                if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }

                if (!(email.isEmpty() && password.isEmpty())){

                    // set visibility
                    progressBar.setVisibility(View.VISIBLE);

                    mFireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                // set visibility
                                progressBar.setVisibility(View.GONE);
                            }

                            else {
                                Intent intHome = new Intent (MainActivity.this, HomePage.class);
                                startActivity(intHome);
                                Toast.makeText(MainActivity.this, "Account Logged In Successfully", Toast.LENGTH_SHORT).show();

                                // set visibility
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }


                else {
                    Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignUp = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intSignUp);
            }
        });


        // Forget Password
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intFgtPass = new Intent(MainActivity.this, first_page_forget_password.class);
                startActivity(intFgtPass);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

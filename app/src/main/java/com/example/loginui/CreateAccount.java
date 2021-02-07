package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +             // at least 1 digit
                    "(?=.*[a-z])" +             // at least 1 lower case letter
                    "(?=.*[A-Z])" +             // at least 1 upper case letter
                    "(?=\\S+$)" +               // no white spaces
                    ".{6,12}" +                 // at least 6 characters and less than 12
                    "$");

    EditText inputName, userName, pin;
    Button signUpBtn;
    TextView login;
    ImageView back;
    CheckBox showPassword;
    ProgressBar progressBar;
    FirebaseAuth mFireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mFireBaseAuth = FirebaseAuth.getInstance();

        inputName = findViewById(R.id.inputTextName);
        userName = findViewById(R.id.userName);
        pin = findViewById(R.id.passwordText);
        login = findViewById(R.id.textLogin);
        signUpBtn = findViewById(R.id.signUp);
        back = findViewById(R.id.backOption);
        progressBar = findViewById(R.id.progressBar);
        showPassword = findViewById(R.id.showTextPassword);

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

        mFireBaseAuth = FirebaseAuth.getInstance();


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String email = userName.getText().toString();
                String password = pin.getText().toString();


                if (email.isEmpty()){
                    userName.setError("Kindly input a valid email");
                    userName.requestFocus();
                }
                // Email Validation
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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
                // Password validation
                else if (!PASSWORD_PATTERN.matcher(password).matches()){
                    pin.setError("Password must include 1 digit, 1 lower case, 1 upper case & at least 6 cha");
                    pin.requestFocus();
                }

                else if (name.isEmpty()){
                    inputName.setError("You are required to fill your name");
                    inputName.requestFocus();
                }

                else if (password.length()<6) {
                    pin.setError("Password must be greater than 6");
                    pin.requestFocus();
                }

               else if (email.isEmpty() && password.isEmpty() && name.isEmpty()){
                    Toast.makeText(CreateAccount.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }

               else if (!(email.isEmpty() && password.isEmpty() && name.isEmpty())){
                   // set visibility
                   progressBar.setVisibility(View.VISIBLE);

                   mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        else {
                            Intent intToHome = new Intent (CreateAccount.this, MainActivity.class);
                            startActivity(intToHome);
                            Toast.makeText(CreateAccount.this, "Account Created Successfully", Toast.LENGTH_LONG).show();

                            // set Gone
                            progressBar.setVisibility(View.GONE);
                        }
                       }
                   });
                }
               else {
                    Toast.makeText(CreateAccount.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (CreateAccount.this, MainActivity.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (CreateAccount.this, MainActivity.class));
                finish();
            }
        });
    }
}

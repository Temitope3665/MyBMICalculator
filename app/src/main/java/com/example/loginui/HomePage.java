package com.example.loginui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Objects;

public class HomePage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    ProgressBar progressBar;
    ImageView menu;
    CardView exercise;
    CardView yoga;
    CardView meditation;
    CardView dietChart;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        progressBar = findViewById(R.id.progressBar);
        exercise = findViewById(R.id.exerciseCardView);
        yoga = findViewById(R.id.yogaCardView);
        meditation = findViewById(R.id.meditationCardView);
        dietChart = findViewById(R.id.dietChartCardView);


        // Exercise Activity
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intExercise = new Intent (HomePage.this, ExerciseActivity.class);
                // progressbar
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intExercise);
                // progressbar
                progressBar.setVisibility(View.GONE);
            }
        });

        // Yoga Activity
        yoga.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intYoga = new Intent(HomePage.this, YogaActivity.class);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intYoga);
            }
        });

        // Meditation Activity
        meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intMed = new Intent(HomePage.this, MeditationActivity.class);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intMed);
                progressBar.setVisibility(View.GONE);
            }
        });

        // Diet Chart Activity
        dietChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intDiet = new Intent(HomePage.this, DietChartActivity.class);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(intDiet);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    // Logout and ABout us Activity
    public void showPopup(View view) {
        PopupMenu popUp = new PopupMenu(this, view);
        popUp.setOnMenuItemClickListener(this);
        popUp.inflate(R.menu.top_right_menu);
        popUp.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.menuAboutUs:
                Intent intAbt = new Intent(HomePage.this, AboutUs.class);
                startActivity(intAbt);
                return true;

            case R.id.menuLogout:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Logout");
                alert.setMessage("Are you sure ?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intOut = new Intent (HomePage.this, MainActivity.class);
                        startActivity(intOut);
                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(HomePage.this, "Logout successfully", Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intIn = new Intent (HomePage.this, HomePage.class);
                        startActivity(intIn);
                    }
                });
                alert.create().show();

                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {

    }
}

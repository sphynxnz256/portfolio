package com.example.gymapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    //method to open MainActivity which holds all our workouts
    public void startWorkouts (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //method to open the user guide
    public void startUserGuide (View v) {
        Intent intent = new Intent(this, UserGuideActivity.class);
        startActivity(intent);

    }
}

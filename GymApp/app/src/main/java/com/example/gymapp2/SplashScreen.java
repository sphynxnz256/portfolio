package com.example.gymapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    //method to open StartActivity
    public void openStartActivity(View v) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}

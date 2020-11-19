package com.example.waiataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setTitle("Waiata");

        //show the splash screen for 3seconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 3s = 3000ms
                openApp();
            }
        }, 3000);

    }

    //open the main activity
    public void openApp () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

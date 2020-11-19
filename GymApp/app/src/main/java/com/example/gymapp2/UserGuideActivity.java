package com.example.gymapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//this activity just holds a string that explains how to use this app
public class UserGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);
    }
}

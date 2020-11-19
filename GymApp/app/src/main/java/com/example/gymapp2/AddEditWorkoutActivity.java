package com.example.gymapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditWorkoutActivity extends AppCompatActivity {
    //public keys for passing data via intents
    public static final String EXTRA_ID = "com.example.gymapp2.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.gymapp2.EXTRA_NAME";

    //editText variables
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        //link editText boxes in XML to editText variables
        editTextName = findViewById(R.id.edit_text_name);

        //get intent passed from MainActivity
        Intent intent = getIntent();

        /*if id is passed in the intent we are editing not adding a new workout and need to
        add the current exercise info to the ui and set the tile to Edit Workout*/
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Workout");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
        } else {
            setTitle("Add Workout");
        }
    }

    //method to save workout
    public void saveWorkout(View v) {
        String name = editTextName.getText().toString();

        //name is required to make a workout so we check user has given one
        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a workout name", Toast.LENGTH_SHORT).show();
            return;
        }

        //create an intent to send data back to activity that called this one
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);

        //if this is not a new workout we need to send the workout id back.
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}

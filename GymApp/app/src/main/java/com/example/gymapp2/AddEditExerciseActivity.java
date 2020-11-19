package com.example.gymapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditExerciseActivity extends AppCompatActivity {
    //public keys for passing data via intents
    public static final String EXTRA_EXERCISE_ADD_EDIT_ID = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_ID";
    public static final String EXTRA_EXERCISE_ADD_EDIT_NAME = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_NAME";
    public static final String EXTRA_EXERCISE_ADD_EDIT_SETS = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_SETS";
    public static final String EXTRA_EXERCISE_ADD_EDIT_REPS = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_REPS";
    public static final String EXTRA_EXERCISE_ADD_EDIT_WEIGHT = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_WEIGHT";
    public static final String EXTRA_EXERCISE_ADD_EDIT_INFO = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_INFO";
    public static final String EXTRA_EXERCISE_ADD_EDIT_VIDEO = "com.example.gymapp.EXTRA_EXERCISE_ADD_EDIT_VIDEO";

    //tag for logs
    private static final String TAG = "AddEditExerciseActivity";

    //editText variables
    private EditText editTextExerciseName;
    private EditText editTextSets;
    private EditText editTextReps;
    private EditText editTextWeight;
    private EditText editTextInfo;
    private EditText editTextVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        //link editText boxes in XML to editText variables
        editTextExerciseName = findViewById(R.id.editText_exercise_name);
        editTextSets = findViewById(R.id.edit_text_sets);
        editTextReps = findViewById(R.id.edit_text_reps);
        editTextWeight = findViewById(R.id.edit_text_weight);
        editTextInfo = findViewById(R.id.edit_text_info);
        editTextVideo = findViewById(R.id.edit_text_video);

        //get intent passed from WorkoutActivity
        Intent intent = getIntent();

        /*if id is passed in the intent we are editing not adding a new exercise and need to
        add the current exercise info to the ui and set the tile to Edit Exercise*/
        if (intent.hasExtra(EXTRA_EXERCISE_ADD_EDIT_ID)) {
            setTitle("Edit Exercise");

            int sets = intent.getIntExtra(EXTRA_EXERCISE_ADD_EDIT_SETS, 1);
            int reps = intent.getIntExtra(EXTRA_EXERCISE_ADD_EDIT_REPS,1 );
            String setsString = Integer.toString(sets);
            String repsString = Integer.toString(reps);

            Log.i(TAG, "onCreate: intent(name): " + intent.getStringExtra(EXTRA_EXERCISE_ADD_EDIT_NAME));
            editTextExerciseName.setText(intent.getStringExtra(EXTRA_EXERCISE_ADD_EDIT_NAME));
            editTextSets.setText(setsString);
            editTextReps.setText(repsString);
            editTextWeight.setText(intent.getStringExtra(EXTRA_EXERCISE_ADD_EDIT_WEIGHT));
            editTextInfo.setText(intent.getStringExtra(EXTRA_EXERCISE_ADD_EDIT_INFO));
            editTextVideo.setText(intent.getStringExtra(EXTRA_EXERCISE_ADD_EDIT_VIDEO));
        } else { //if there was no id, we're creating a new exercise and set the title to Add Exercise
            setTitle("Add Exercise");
        }
    }

    //method to save exercise
    public void saveExercise(View v) {
        //get the data from the edit boxes. try catches to deal with no numbers being added to sets and reps
        int sets = 0, reps = 0;
        String name = editTextExerciseName.getText().toString();
        try {
            sets = Integer.parseInt(editTextSets.getText().toString());
        } catch (Exception e) {
            Log.i(TAG, "saveExercise: sets couldn't be set");
        }
        try {
            reps = Integer.parseInt(editTextReps.getText().toString());
        } catch (Exception e) {
            Log.i(TAG, "saveExercise: reps couldn't be set");
        }
        String weight = editTextWeight.getText().toString();
        String info = editTextInfo.getText().toString();
        String video = editTextVideo.getText().toString();

        //name is required to make an exercise so we check user has given one
        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please enter an exercise name", Toast.LENGTH_SHORT).show();
            return;
        }

        //log to see what data is getting sent to the activity that called it
        Log.i(TAG, "saveExercise: data:\nname: " + name + "\nsets: " + sets + "\nreps: " + reps
        + "\nweight: " + weight + "\ninfo: " + info + "\nvideo: " + video);

        //create an intent to send data back to activity that called this one
        Intent data = new Intent();
        data.putExtra(EXTRA_EXERCISE_ADD_EDIT_NAME, name);
        data.putExtra(EXTRA_EXERCISE_ADD_EDIT_SETS, sets);
        data.putExtra(EXTRA_EXERCISE_ADD_EDIT_REPS, reps);
        data.putExtra(EXTRA_EXERCISE_ADD_EDIT_WEIGHT, weight);
        data.putExtra(EXTRA_EXERCISE_ADD_EDIT_INFO, info);
        data.putExtra(EXTRA_EXERCISE_ADD_EDIT_VIDEO, video);

        //if this is not a new exercise we need to send the exercise id back.
        int id = getIntent().getIntExtra(EXTRA_EXERCISE_ADD_EDIT_ID, -1);
        if (id != -1)
            data.putExtra(EXTRA_EXERCISE_ADD_EDIT_ID, id);

        setResult(RESULT_OK, data);
        finish();
    }
}

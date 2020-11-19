package com.example.gymapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ExerciseActivity extends AppCompatActivity {
    //keys for requests
    public static final int EDIT_EXERCISE_BUTTON_REQUEST = 5;

    //keys for passing data via intents
    public static final String EXTRA_EXERCISE_ID = "com.example.gymapp2.EXTRA_EXERCISE_ID";
    public static final String EXTRA_EXERCISE_NAME = "com.example.gymapp2.EXTRA_EXERCISE_NAME";
    public static final String EXTRA_EXERCISE_SETS = "com.example.gymapp.EXTRA_EXERCISE_SETS";
    public static final String EXTRA_EXERCISE_REPS = "com.example.gymapp.EXTRA_EXERCISE_REPS";
    public static final String EXTRA_EXERCISE_WEIGHT = "com.example.gymapp.EXTRA_EXERCISE_WEIGHT";
    public static final String EXTRA_EXERCISE_INFO = "com.example.gymapp.EXTRA_EXERCISE_INFO";
    public static final String EXTRA_EXERCISE_VIDEO = "com.example.gymapp.EXTRA_EXERCISE_VIDEO";
    public static final String EXTRA_EXERCISE_WORKOUT_ID = "com.example.gymapp.EXTRA_EXERCISE_WORKOUT_ID";

    //tag for logs
    private static final String TAG = "ExerciseActivity";

    //text view variables
    private TextView textViewSets;
    private TextView textViewReps;
    private TextView textViewWeight;
    private TextView textViewInfoLink;
    private TextView textViewVideoLink;

    //view model variable
    private ExerciseViewModel exerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //link the text views from XML to variables
        textViewSets = findViewById(R.id.text_view_exercise_sets);
        textViewReps = findViewById(R.id.text_view_exercise_reps);
        textViewWeight = findViewById(R.id.text_view_exercise_weight);
        textViewInfoLink = findViewById(R.id.text_view_info_link);
        textViewVideoLink = findViewById(R.id.text_view_video_link);

        //create a view model provider for our view model.
        exerciseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ExerciseViewModel.class);

        //set exercise title
        String exerciseName = getIntent().getStringExtra(EXTRA_EXERCISE_NAME);
        setTitle(exerciseName);

        //get information and set text views with this information
        Intent intent = getIntent();
        String textSets = "Sets: " + intent.getIntExtra(EXTRA_EXERCISE_SETS, 1);
        String textReps = "Reps: " + intent.getIntExtra(EXTRA_EXERCISE_REPS, 1);
        String textWeight = "Weight: " + intent.getStringExtra(EXTRA_EXERCISE_WEIGHT);
        textViewSets.setText(textSets);
        textViewReps.setText(textReps);
        textViewWeight.setText(textWeight);
        textViewInfoLink.setText(intent.getStringExtra(EXTRA_EXERCISE_INFO));
        textViewVideoLink.setText(intent.getStringExtra(EXTRA_EXERCISE_VIDEO));
    }

    //method to edit exercise (currently not working as intended)
    //extracts info from intent
    public void editExercise(View v) {
        Intent oldIntent = getIntent();
        int id = oldIntent.getIntExtra(EXTRA_EXERCISE_ID, -1);
        String name = getTitle().toString();
        int sets = Integer.parseInt((textViewSets.getText().toString()).substring(6));
        int reps = Integer.parseInt((textViewReps.getText().toString()).substring(6));
        String weight = (textViewWeight.getText().toString()).substring(8);
        String info = textViewInfoLink.getText().toString();
        String video = textViewVideoLink.getText().toString();

        //log to see what data is being sent to AddEditExerciseActivity
        Log.i(TAG, "editExercise: data:\nname: " + name + "\nsets: " + sets + "\nreps: " + reps
                + "\nweight: " + weight + "\ninfo: " + info + "\nvideo: " + video);

        //creates new intent and adds info to it
        Intent intent = new Intent (ExerciseActivity.this, AddEditExerciseActivity.class);
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_ID, id);
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_NAME, name);
        Log.i(TAG, "editExercise: intent(name): "
                + intent.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_NAME));
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_SETS, sets);
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_REPS, reps);
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_WEIGHT, weight);
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_INFO, info);
        intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_VIDEO, video);
        startActivityForResult(intent, EDIT_EXERCISE_BUTTON_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //gets data back from AddEditExerciseActivity and updates the exercise
        if (requestCode == EDIT_EXERCISE_BUTTON_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_ID, -1);

            //we need the exercises id in order to update it.
            if (id == -1) {
                Toast.makeText(this, "Exercise can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            //get the data that was sent back from AddEditExerciseActivity
            String name = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_NAME);
            int sets = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_SETS, 1);
            int reps = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_REPS, 1);
            String weight = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_WEIGHT);
            String info = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_INFO);
            String video = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_VIDEO);
            int workout_id = getIntent().getIntExtra(EXTRA_EXERCISE_WORKOUT_ID, -1);

            //log to check what data has been received
            Log.i(TAG, "onActivityResult: exercise data:\nname: " + name + "\nsets: " + sets
            + "\nreps: " + reps + "\nweight: " + weight + "\ninfo: " + info + "\nvideo: " + video +
                    "\n workout id: " + workout_id);

            //create the exercise to update our database with.
            Exercise exercise = new Exercise(name, sets, reps, weight, info, video, workout_id);
            exercise.setId(id);
            exerciseViewModel.update(exercise);

            //update the exercise name
            setTitle(name);

            //set the new values in our exercise ui
            String textSets = "Sets: " + sets;
            String textReps = "Reps: " + reps;
            String textWeight = "Weight: " + weight;

            textViewSets.setText(textSets);
            textViewReps.setText(textReps);
            textViewWeight.setText(textWeight);
            textViewInfoLink.setText(info);
            textViewVideoLink.setText(video);

            Toast.makeText(this, "Exercise updated", Toast.LENGTH_SHORT).show();
        } else { //if exercise wasn't updated for some reason send a toast
            Toast.makeText(this, "Exercise not updated", Toast.LENGTH_SHORT).show();
        }
    }
}

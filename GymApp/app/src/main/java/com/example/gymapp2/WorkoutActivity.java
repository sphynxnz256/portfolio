package com.example.gymapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WorkoutActivity extends AppCompatActivity {
    //keys for requests
    public static final int ADD_EXERCISE_REQUEST = 3;
    public static final int EDIT_EXERCISE_REQUEST = 4;

    //keys for passing data via intents
    public static final String EXTRA_WORKOUT_ID = "com.example.gymapp2.EXTRA_WORKOUT_ID";
    public static final String EXTRA_WORKOUT_NAME = "com.example.gymapp2.EXTRA_WORKOUT_NAME";

    //public class variable (we needed it to get the workout id from this activity to the repository)
    public static int queryId;

    //tag for logs
    private static final String TAG = "WorkoutActivity";

    //variable for the view model
    private ExerciseViewModel exerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        /*set the global variable with our workout id so the dao can find the correct exercises for
        the correct workout*/
        queryId = getIntent().getIntExtra(EXTRA_WORKOUT_ID, -1);

        //set workout name
        String workoutName = getIntent().getStringExtra(EXTRA_WORKOUT_NAME);
        setTitle(workoutName);

        //create recyclerview and attach a layout manager
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //create the exercise adapter and attach it to the recyclerview
        final ExerciseAdapter adapter = new ExerciseAdapter();
        recyclerView.setAdapter(adapter);

        //create view model for workout table and set a new observer to it
        exerciseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ExerciseViewModel.class);
        exerciseViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                adapter.submitList(exercises);
            }
        });

        //create a item touch helper for swipe to delete functionality
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                exerciseViewModel.delete(adapter.getExerciseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(WorkoutActivity.this, "Exercise deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //open the exercise when the item in the recyclerview is clicked on
        adapter.setOnExerciseItemClickListener(new ExerciseAdapter.OnExerciseItemClickListener() {
            @Override
            public void onExerciseItemClick(Exercise exercise) {
                Intent intent = new Intent(WorkoutActivity.this, ExerciseActivity.class);
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_ID, exercise.getId());
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_NAME, exercise.getName());
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_SETS, exercise.getSets());
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_REPS, exercise.getReps());
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_WEIGHT, exercise.getWeight());
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_INFO, exercise.getWeb_link());
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_VIDEO, exercise.getVideo_link());
                int id = getIntent().getIntExtra(EXTRA_WORKOUT_ID, -1);
                intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_WORKOUT_ID, id);
                startActivity(intent);
            }
        });

        //open edit exercise when the item in the recyclerview is long clicked on
        adapter.setOnExerciseItemLongCLickListener(new ExerciseAdapter.OnExerciseLongClickListener() {
            @Override
            public void onExerciseLongClickListener(Exercise exercise) {
                Intent intent = new Intent(WorkoutActivity.this, AddEditExerciseActivity.class);
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_ID, exercise.getId());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_NAME, exercise.getName());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_SETS, exercise.getSets());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_REPS, exercise.getReps());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_WEIGHT, exercise.getWeight());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_INFO, exercise.getWeb_link());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_VIDEO, exercise.getVideo_link());
                startActivityForResult(intent, EDIT_EXERCISE_REQUEST);
                Log.i(TAG, "onExerciseLongClickListener: intent: " + intent);
            }
        });

        //creates a floating button to add a new workout
        FloatingActionButton buttonAddExercise = findViewById(R.id.button_add_exercise);
        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, AddEditExerciseActivity.class);
                startActivityForResult(intent, ADD_EXERCISE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //receives the data for adding new exercise and adds it to the database
        if(requestCode == ADD_EXERCISE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_NAME);
            int sets = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_SETS, 1);
            int reps = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_REPS, 1);
            String weight = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_WEIGHT);
            String info = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_INFO);
            String video = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_VIDEO);
            int workoutId = getIntent().getIntExtra(EXTRA_WORKOUT_ID, -1);

            Exercise exercise = new Exercise(name, sets, reps, weight, info, video, workoutId);
            exerciseViewModel.insert(exercise);

            Toast.makeText(this, "Exercise saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_EXERCISE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Exercise can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_NAME);
            int sets = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_SETS, 1);
            int reps = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_REPS, 1);
            String weight = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_WEIGHT);
            String info = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_INFO);
            String video = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ADD_EDIT_VIDEO);
            int workoutId = getIntent().getIntExtra(EXTRA_WORKOUT_ID, -1);

            Exercise exercise = new Exercise(name, sets, reps, weight, info, video, workoutId);
            exercise.setId(id);
            try {
                exerciseViewModel.update(exercise);
            } catch (Exception e) {
                Toast.makeText(this, "Error, Exercise not saved", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Exercise updated", Toast.LENGTH_SHORT).show();
        } else {
            //message for if exercise is not saved
            Toast.makeText(this, "Exercise not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.workout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_exercises:
                exerciseViewModel.deleteAllExercises();
                Toast.makeText(this, "All exercises deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.example.gymapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //keys for requests
    public static final int ADD_WORKOUT_REQUEST = 1;
    public static final int EDIT_WORKOUT_REQUEST = 2;

    //view model variables
    private WorkoutViewModel workoutViewModel;
    private ExerciseViewModel exerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adds button to add a new workout
        FloatingActionButton buttonAddWorkout = findViewById(R.id.button_add_workout);
        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditWorkoutActivity.class);
                startActivityForResult(intent, ADD_WORKOUT_REQUEST);
            }
        });

        //attach recycler view in java to recycler view in xml
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //create adapter and attach it to the recycler view
        final WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        //create view model for workout table and set a new observer to it
        workoutViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(WorkoutViewModel.class);
        workoutViewModel.getAllWorkouts().observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                adapter.submitList(workouts);
            }
        });

        //create exercise view model
        exerciseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ExerciseViewModel.class);

        //create a touch helper and set it to delete workouts when swiped
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                WorkoutActivity.queryId = (adapter.getWorkoutAt(viewHolder.getAdapterPosition())).getId();
                exerciseViewModel.deleteAllExercises();
                workoutViewModel.delete(adapter.getWorkoutAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Workout deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //opens the workout when item is clicked in the recyclerview
        adapter.setOnItemClickListener(new WorkoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Workout workout) {
                Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.EXTRA_WORKOUT_ID, workout.getId());
                intent.putExtra(WorkoutActivity.EXTRA_WORKOUT_NAME, workout.getName());
                startActivity(intent);
            }
        });

        //opens edit workout when item is long clicked in the recyclerview
        adapter.setOnItemLongClickListener(new WorkoutAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Workout workout) {
                Intent intent = new Intent(MainActivity.this, AddEditWorkoutActivity.class);
                intent.putExtra(AddEditWorkoutActivity.EXTRA_ID, workout.getId());
                intent.putExtra(AddEditWorkoutActivity.EXTRA_NAME, workout.getName());
                startActivityForResult(intent, EDIT_WORKOUT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //gets info from add workout and adds it to the database
        if (requestCode == ADD_WORKOUT_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditWorkoutActivity.EXTRA_NAME);

            Workout workout = new Workout(name);
            workoutViewModel.insert(workout);

            Toast.makeText(this, "Workout saved", Toast.LENGTH_SHORT).show();

        //gets info from edit workout and updates the database
        } else if (requestCode == EDIT_WORKOUT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditWorkoutActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Workout can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddEditWorkoutActivity.EXTRA_NAME);

            Workout workout = new Workout(name);
            workout.setId(id);
            workoutViewModel.update(workout);

            Toast.makeText(this, "Workout updated", Toast.LENGTH_SHORT).show();
        } else {
            //message for if workout is not saved
            Toast.makeText(this, "Workout not saved", Toast.LENGTH_SHORT).show();

        }
    }

    //adds menu with delete all workouts option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //adds functionally to delete all workouts option in menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_workouts:
                /*code for deleting all exercises when deleting all exercises not yet complete
                currently memory leak as exercises will remain even though the workout does not
                find a way to extract workouts from LiveData
                workouts = list of workouts
                for (workout : workouts) {
                    WorkoutActivity.queryId = workout.getId();
                    exerciseViewModel.deleteAllExercises();
                }*/

                workoutViewModel.deleteAllWorkouts();
                Toast.makeText(this, "All workouts deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

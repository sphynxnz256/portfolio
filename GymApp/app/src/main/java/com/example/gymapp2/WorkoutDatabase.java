package com.example.gymapp2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//this class builds our database
@Database(entities = {Workout.class, Exercise.class}, version = 1)
public abstract class WorkoutDatabase extends RoomDatabase {

    private static WorkoutDatabase instance;

    public abstract WorkoutDao workoutDao();
    public abstract ExerciseDao exerciseDao();

    //creates the single instance of the  workout database
    public static synchronized WorkoutDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WorkoutDatabase.class, "workout_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

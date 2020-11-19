package com.example.gymapp2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//interfaces for exercise table
@Dao
public interface ExerciseDao {

    @Insert
    void insert(Exercise exercise);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

    @Query("DELETE FROM exercise_table WHERE workout_id = :query_id")
    void deleteAllExercises(int query_id);

    @Query("SELECT * FROM exercise_table WHERE workout_id = :query_id")
    LiveData<List<Exercise>> getAllExercises(int query_id);

}

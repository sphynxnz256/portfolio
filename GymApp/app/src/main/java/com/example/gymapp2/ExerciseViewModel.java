package com.example.gymapp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//view model for exercise table
public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepository repository;
    private LiveData<List<Exercise>> allExercises;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExerciseRepository(application);
        allExercises = repository.getAllExercises();
    }

    //rapper methods for database modification
    public void insert(Exercise exercise) {
        repository.insert(exercise);
    }

    public void update(Exercise exercise) {
        repository.update(exercise);
    }

    public void delete(Exercise exercise) {
        repository.delete(exercise);
    }

    public void deleteAllExercises() {
        repository.deleteAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }
}

package com.example.gymapp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//viewmodel for workout table
public class WorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository repository;
    private LiveData<List<Workout>> allWorkouts;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        repository = new WorkoutRepository(application);
        allWorkouts = repository.getAllWorkouts();
    }

    //rapper methods for database modification
    public void insert(Workout workout) {
        repository.insert(workout);
    }

    public void update(Workout workout) {
        repository.update(workout);
    }

    public void delete(Workout workout) {
        repository.delete(workout);
    }

    public void deleteAllWorkouts() {
        repository.deleteAllWorkouts();
    }

    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }
}

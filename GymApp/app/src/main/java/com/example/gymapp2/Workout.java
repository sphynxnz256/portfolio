package com.example.gymapp2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//infomation to create a workout entity
@Entity(tableName = "workout_table")
public class Workout {

    //variables that represent the columns in the table
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id")
    private int id;

    private String name;

    //constructor
    public Workout(String name) {
        this.name = name;
    }

    //getters and setters for variables
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}

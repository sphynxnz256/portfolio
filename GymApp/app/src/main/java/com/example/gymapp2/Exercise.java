package com.example.gymapp2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//information to create an exercise entity
@Entity(tableName = "exercise_table")
public class Exercise {

    //variables that will represent the columns in the table
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int sets;

    private int reps;

    private String weight;

    private String web_link;

    private String video_link;

    //foreign key that shows which workout an exercise belongs to
    @ForeignKey(
            entity = Workout.class,
            parentColumns = "workout_id",
            childColumns = "workout_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
    @ColumnInfo(name = "workout_id")
    private int workout_id;

    //constructor
    public Exercise(String name, int sets, int reps, String weight, String web_link, String video_link, int workout_id) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.web_link = web_link;
        this.video_link = video_link;
        this.workout_id = workout_id;
    }

    //getters and setters for variables
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public String getWeight() {
        return weight;
    }

    public String getWeb_link() {
        return web_link;
    }

    public String getVideo_link() {
        return video_link;
    }

    public int getWorkout_id() {
        return workout_id;
    }
}

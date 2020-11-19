package com.example.waiataapp.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "song_table")
public class Song {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private int easy;

    private int medium;

    private int hard;

    private String maoriLyrics;

    private String englishLyrics;

    private int thumbnail;

    //constructor
    public Song(String title, int easy, int medium, int hard, String maoriLyrics, String englishLyrics, int thumbnail) {
        this.title = title;
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
        this.maoriLyrics = maoriLyrics;
        this.englishLyrics = englishLyrics;
        this.thumbnail = thumbnail;
    }

    //setter for Database to generate the id
    public void setId(int id) {
        this.id = id;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getEasy() {
        return easy;
    }

    public int getMedium() {
        return medium;
    }

    public int getHard() {
        return hard;
    }

    public String getMaoriLyrics() {
        return maoriLyrics;
    }

    public String getEnglishLyrics() {
        return englishLyrics;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
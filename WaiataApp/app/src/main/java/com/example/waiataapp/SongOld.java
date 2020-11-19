package com.example.waiataapp;

public class SongOld {

    private String Title;
    private String Easy;
    private String Medium;
    private String Hard;
    private String Lyrics;
    private int Thumbnail;


    public SongOld(String title, String easy, String medium, String hard, String lyrics, int thumbnail) {
        Title = title;
        Easy = easy;
        Medium = medium;
        Hard = hard;
        Thumbnail = thumbnail;
        Lyrics = lyrics;
    }

    public String getTitle() {
        return Title;
    }

    public String getEasy() {
        return Easy;
    }

    public String getMedium() {
        return Medium;
    }

    public String getHard() {
        return Hard;
    }

    public String getLyrics() {
        return Lyrics;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setEasy(String easy) {
        Easy = easy;
    }

    public void setMedium(String medium) {
        Medium = medium;
    }

    public void setHard(String hard) {
        Hard = Hard;
    }

    public void setLyrics(String lyrics) {
        Lyrics = lyrics;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }


}

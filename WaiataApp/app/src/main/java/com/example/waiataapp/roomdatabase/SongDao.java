package com.example.waiataapp.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SongDao {
    @Insert
    void insert(Song song);

    @Update
    void update(Song song);

    @Delete
    void delete(Song song);

    @Query("DELETE FROM song_table")
    void deleteAllSongs();

    @Query("SELECT * FROM song_table")
    LiveData<List<Song>> getAllSongs();

}

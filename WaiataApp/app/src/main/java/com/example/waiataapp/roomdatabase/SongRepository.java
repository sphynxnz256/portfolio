package com.example.waiataapp.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SongRepository {
    private SongDao songDao;

    //lists to store songs for different fragments
    private LiveData<List<Song>> allSongs;


    public SongRepository(Application application) {
        SongDatabase database =SongDatabase.getInstance(application);
        songDao = database.songDao();

        allSongs = songDao.getAllSongs();

    }

    public void insert(Song song) {
        new InsertSongAsyncTask(songDao).execute(song);
    }

    public void update (Song song) {
        new UpdateSongAsyncTask(songDao).execute(song);
    }

    public void delete (Song song) {
        new DeleteSongAsyncTask(songDao).execute(song);
    }

    public void deleteAllSongs () {
        new DeleteAllSongsAsyncTask(songDao).execute();
    }


    public LiveData<List<Song>> getAllSongs() {
        return allSongs;
    }

    //
    private static class InsertSongAsyncTask extends AsyncTask<Song, Void, Void> {
        private SongDao songDao;
        private InsertSongAsyncTask(SongDao songDao) {
            this.songDao = songDao;
        }

        @Override
        protected Void doInBackground(Song... songs) {
            songDao.insert(songs[0]);
            return null;
        }
    }

    private static class UpdateSongAsyncTask extends AsyncTask<Song, Void, Void> {
        private SongDao songDao;
        private UpdateSongAsyncTask(SongDao songDao) {
            this.songDao = songDao;
        }

        @Override
        protected Void doInBackground(Song... songs) {
            songDao.update(songs[0]);
            return null;
        }
    }

    private static class DeleteSongAsyncTask extends AsyncTask<Song, Void, Void> {
        private SongDao songDao;
        private DeleteSongAsyncTask(SongDao songDao) {
            this.songDao = songDao;
        }

        @Override
        protected Void doInBackground(Song... songs) {
            songDao.delete(songs[0]);
            return null;
        }
    }

    private static class DeleteAllSongsAsyncTask extends AsyncTask<Void, Void, Void> {
        private SongDao songDao;
        private DeleteAllSongsAsyncTask(SongDao songDao) {
            this.songDao = songDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            songDao.deleteAllSongs();
            return null;
        }
    }
}

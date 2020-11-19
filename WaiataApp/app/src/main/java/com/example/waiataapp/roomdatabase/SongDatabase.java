package com.example.waiataapp.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.waiataapp.R;

@Database(entities = {Song.class}, version = 1)
public abstract class SongDatabase extends RoomDatabase {

    private static SongDatabase instance;

    public abstract SongDao songDao();

    public static synchronized SongDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SongDatabase.class, "song_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private SongDao songDao;

        private PopulateDbAsyncTask(SongDatabase db) {
            songDao = db.songDao();
        }

        /*Database entries*/
        /* songDao.insert(new Song("", , , , "", "", )); */
        // ō ā ū ī
        @Override
        protected Void doInBackground(Void... voids) {
            //E Kore Koe E Ngaro
            songDao.insert(new Song("E Kore Koe E Ngaro",
                    R.raw.ekorekoe_1, R.raw.ekorekoe_2, R.raw.ekorekoe_3,

                    "E kore koe e ngaro he kākano i ruia\n" +
                            "Kākano a te Kaahu e tū nei e\n" +
                            "Te Kōpū Mānia, te ngāwhā whakatupu\n" +
                            "Ka tupu he tangata, rere ki te ao\n" +
                            "Horahia Matariki, kūmea ngā waka\n" +
                            "Herea ki te pou o te aroha e\n" +
                            "Te Atairangikaahu, tāiri kei runga\n" +
                            "Ko Kīngi Tūheitia ki te whenua e\n" +
                            "Piki ake Tāwhaki, tāhūhū matapū\n" +
                            "Ngā kete wānanga e toru e\n" +
                            "Whītikitia rā, ka turuturu iho\n" +
                            "E ko Te Kuratini o Waikato e.",

                    "You are not lost, seed of heaven\n" +
                            "Kākano a te kāhu stand tall\n" +
                            "Te Kōpū Mānia, cultivate new growth\n" +
                            "Foster this person of the world\n" +
                            "Matariki on display, draw in all canoes\n" +
                            "Bind them to the pots of support and care\n" +
                            "Te Atairangikaahu, fly above\n" +
                            "Kingi Tūheitia, hold steadfast below\n" +
                            "Tāwhaki ascend, prepare the house\n" +
                            "For the three baskets of knowledge\n" +
                            "Bind them, fasten them\n" +
                            "To Waikato Institue of Technology",

                    R.drawable.placeholderimage));

            songDao.insert(new Song("song2", R.raw.hemaimaiaroha_1, R.raw.hemaimaiaroha_2,
                    R.raw.hemaimaiaroha_3, "song2 maoriLyrics", "song2 englishLyrics",
                    R.drawable.placeholderimage));

            songDao.insert(new Song("song3", R.raw.itewhare_1, R.raw.itewhare_2,
                    R.raw.itewhare_3, "song3 maoriLyrics", "song3 englishLyrics",
                    R.drawable.placeholderimage));

            songDao.insert(new Song("song4", R.raw.puatekowhai_1, R.raw.puatekowhai_2,
                    R.raw.puatekowhai_3, "song4 maoriLyrics", "song4 englishLyrics",
                    R.drawable.placeholderimage));

            songDao.insert(new Song("song5", R.raw.pupuketehihiri_1, R.raw.pupuketehihiri_2,
                    R.raw.pupuketehihiri_3, "song5 maoriLyrics", "song5 englishLyrics",
                    R.drawable.placeholderimage));

            songDao.insert(new Song("song6", R.raw.tutiramainga_1, R.raw.tutiramainga_2,
                    R.raw.tutiramainga_3, "song6 maoriLyrics", "song6 englishLyrics",
                    R.drawable.placeholderimage));

            songDao.insert(new Song("song7", R.raw.waikatoteawa_1, R.raw.waikatoteawa_2,
                    R.raw.waikatoteawa_3, "song7 maoriLyrics", "song7 englishLyrics",
                    R.drawable.placeholderimage));

            return null;
        }
    }

}

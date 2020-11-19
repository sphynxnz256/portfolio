package com.example.waiataapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class WaiataPage extends AppCompatActivity {


    //variables for the components and the song difficulties
    private Uri uriEz; //path to easy difficulty
    private Uri uriMed; //path to medium diff
    private Uri uriHd; //path to hard diff
    private VideoView video;
    private Button harder;
    private Button easier;
    private TextView diff; //Says what difficulty the song is currently on
    private TextView txtHelp; //tells user what each difficulty includes
    private Button changeLang;
    private String currentLang = "Maori";
    private String engLyrics;
    private String maoriLyrics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiata_page);

        Intent intent = getIntent();

        video = findViewById(R.id.song_display);
        String videoPath = "android.resource://" + getPackageName() + "/"; //set a template for the video path
        uriEz = Uri.parse(videoPath + intent.getIntExtra("Easy", 0));
        uriMed = Uri.parse(videoPath + intent.getIntExtra("Medium", 0));
        uriHd = Uri.parse(videoPath + intent.getIntExtra("Hard", 0));
        engLyrics = intent.getStringExtra("EnglishLyrics");
        maoriLyrics = intent.getStringExtra("MaoriLyrics");
        //These finish the path for the videos based on the extras passed from recycler

        video.setVideoURI(uriEz); //Starts with the easy difficulty on first loading
        video.start(); //sets the video to play straight away

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video); //attaches the media player to the video; got this from another app so hopefully wont have probelms

        harder = findViewById(R.id.harder);
        easier = findViewById(R.id.easier);
        diff = findViewById(R.id.current_diff);
        changeLang = findViewById(R.id.change_lang);
        //Shows/hides the help text
        Button btnHelp = findViewById(R.id.help_button);
        final TextView lyrics = findViewById(R.id.lyrics);
        txtHelp = findViewById(R.id.diff_help);
        diff.setText(R.string.difficulty_easy); //Starts on easy
        lyrics.setText(intent.getStringExtra("MaoriLyrics")); //Will read in the lyrics from the database as an extra.

        harder.setOnClickListener(new View.OnClickListener() {
            //@SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //If clicked and the diff is on easy, activates the easier button again and changes the text colour
                if (diff.getText().toString().equals("Easy")) {
                    easier.setClickable(true);
                    easier.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    diff.setText(R.string.difficulty_medium);
                    video.setVideoURI(uriMed); //Change vid to medium diff
                } else {
                    //Can't be clicked when the diff is hard so only other option is medium.
                    //Disables itself and changes diff to hard
                    diff.setText(R.string.difficulty_hard);
                    video.setVideoURI(uriHd);
                    harder.setClickable(false);
                    harder.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                }
            }
        });
        easier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Same as hard except other way round with disabling/enabling
                if (diff.getText().toString().equals("Medium")) {
                    easier.setClickable(false);
                    diff.setText(R.string.difficulty_easy);
                    video.setVideoURI(uriEz);
                    easier.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                } else {
                    diff.setText(R.string.difficulty_medium);
                    video.setVideoURI(uriMed);
                    harder.setClickable(true);
                    harder.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                }
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Turns the help text off or on depending on the current state.
                if (txtHelp.getVisibility() == View.VISIBLE) {
                    txtHelp.setVisibility(View.GONE);
                    lyrics.setVisibility(View.VISIBLE);
                    changeLang.setVisibility(View.VISIBLE);
                } else {
                    txtHelp.setVisibility(View.VISIBLE);
                    lyrics.setVisibility(View.GONE);
                    changeLang.setVisibility(View.GONE);
                }
            }
        });
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLang.equals("Maori")){
                    currentLang = "English";
                    changeLang.setText(currentLang);
                    lyrics.setText(engLyrics);
                }
                else{
                    currentLang = "Maori";
                    changeLang.setText(currentLang);
                    lyrics.setText(maoriLyrics);
                }
            }
        });
    }
}

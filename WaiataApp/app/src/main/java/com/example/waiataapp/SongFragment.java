package com.example.waiataapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiataapp.roomdatabase.Song;
import com.example.waiataapp.roomdatabase.SongViewModel;
import java.util.List;

public class SongFragment extends Fragment {
    private LiveData<List<Song>> allSongs;


    //List<Song> SongArr; //sets list for data to be entered

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_song, container, false);

        //starts recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_id);
        //sets amount of cards to be displayed by grid format
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        final RecyclerViewAdapter myAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(myAdapter);

        SongViewModel songViewModel = new ViewModelProvider(requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(SongViewModel.class);
        songViewModel.getAllSongs().observe(requireActivity(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                myAdapter.submitList(songs);
            }
        });

        myAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                Intent intent  = new Intent(getActivity(), WaiataPage.class);


                //passses data to song activity
                //"name" is the key that needs to be called on song activity
                intent.putExtra("Easy", song.getEasy());
                intent.putExtra("Medium", song.getMedium());
                intent.putExtra("Hard", song.getHard());
                intent.putExtra("EnglishLyrics", song.getEnglishLyrics());
                intent.putExtra("MaoriLyrics", song.getMaoriLyrics());
                //start activity
                startActivity(intent);
            }
        });

        return view;
    }
}

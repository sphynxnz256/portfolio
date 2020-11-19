package com.example.waiataapp;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiataapp.roomdatabase.Song;


public class RecyclerViewAdapter extends ListAdapter<Song, RecyclerViewAdapter.MyViewHolder> {
    private OnItemClickListener listener;

    public RecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Song> DIFF_CALLBACK = new DiffUtil.ItemCallback<Song>() {
        @Override
        public boolean areItemsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getEnglishLyrics().equals(newItem.getEnglishLyrics()) &&
                    oldItem.getMaoriLyrics().equals(newItem.getMaoriLyrics());
        }
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.cardview_item_song, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Song currentSong = getItem(position);
        holder.text_song_title.setText(currentSong.getTitle());
        holder.imgage_song_thumbnail.setImageResource(currentSong.getThumbnail());
    }

    public Song getSongAt (int position) {
        return getItem(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_song_title;
        ImageView imgage_song_thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_song_title = itemView.findViewById(R.id.song_title_id);
            imgage_song_thumbnail = itemView.findViewById(R.id.song_img_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Song song);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

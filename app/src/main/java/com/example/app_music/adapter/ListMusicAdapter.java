package com.example.app_music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_music.R;
import com.example.app_music.domain.Song;

import java.util.ArrayList;
import java.util.List;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ViewHolder> {
    Context context;
    List<Song> songs;

    public ListMusicAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_list_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.singer.setText(song.getSingers());
        holder.name.setText(song.getSong_name());
        holder.index.setText(position + 1+" ");
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView index, name, singer;
        ImageView img_love;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singer = itemView.findViewById(R.id.txt_name_singer_list);
            img_love = itemView.findViewById(R.id.img_view_list_music_love);
            index = itemView.findViewById(R.id.txt_list_music_index);
            name = itemView.findViewById(R.id.txt_list_music_song);
        }

    }
}

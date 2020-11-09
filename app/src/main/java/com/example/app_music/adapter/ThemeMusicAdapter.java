package com.example.app_music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_music.R;
import com.example.app_music.domain.ThemeSong;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThemeMusicAdapter extends RecyclerView.Adapter<ThemeMusicAdapter.ViewHolder> {
    List<ThemeSong> themeSongList;
    Context context;

    public ThemeMusicAdapter(List<ThemeSong> themeSongList, Context context) {
        this.themeSongList = themeSongList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_list_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThemeSong themeSong = themeSongList.get(position);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/appmusic-96a26.appspot.com/o/image%2Fspeaker-5115559_1920.jpg?alt=media&token=9d744739-0322-4289-a23b-90280c1c9d93").fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return themeSongList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_view_content_theme);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}

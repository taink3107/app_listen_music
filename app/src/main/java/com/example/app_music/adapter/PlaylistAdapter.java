package com.example.app_music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_music.R;
import com.example.app_music.domain.Playlist;
import com.example.app_music.domain.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {


    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView, img_background;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.content_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.txt_view_name_playlist);
            viewHolder.imageView = convertView.findViewById(R.id.img_view_playlist);
            viewHolder.img_background = convertView.findViewById(R.id.image_background_playlist_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.get().load(playlist.getImg_playlist_URL()).fit().centerCrop().into(viewHolder.img_background);
        Picasso.get().load(playlist.getImg_playlist_URL()).fit().centerCrop().into(viewHolder.imageView);
        viewHolder.textView.setText(playlist.getName_playlist());

        return convertView;
    }
}

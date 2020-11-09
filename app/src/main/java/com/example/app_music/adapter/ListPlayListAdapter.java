package com.example.app_music.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_music.R;
import com.example.app_music.component.ListMusicActivity;
import com.example.app_music.domain.Playlist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListPlayListAdapter extends RecyclerView.Adapter<ListPlayListAdapter.ViewHoder> {
    Context context;
    List<Playlist> playlists;

    public ListPlayListAdapter(Context context, List<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.content_list_playlist, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Playlist playlist = playlists.get(position);
        Picasso.get().load(playlist.getImg_playlist_URL()).fit().centerCrop().into(holder.imageView);
        holder.txt_name.setText(playlist.getName_playlist());
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_name, txt_singer;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_view_list_playlist);
            txt_name = itemView.findViewById(R.id.txt_name_list_playlist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListMusicActivity.class);
                    intent.putExtra("item_playlist", playlists.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}

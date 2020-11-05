package com.example.app_music.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_music.R;
import com.example.app_music.domain.Song;

public class Fragment_Song_Info extends Fragment {
    View view;
    TextView txtSongName,txtSongSinger,txtSongType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_song_info,container,false);
        Song song = (Song) getArguments().getSerializable("songInfo");
        txtSongName = view.findViewById(R.id.txtSongName);
        txtSongSinger = view.findViewById(R.id.txtSongSinger);
        txtSongType = view.findViewById(R.id.txtSongType);
        txtSongName.setText(song.getSong_name());
        txtSongSinger.setText(song.getSingers());
        txtSongType.setText(song.getType_song());
        return view;
    }
}

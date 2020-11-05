package com.example.app_music.ui.Fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_music.R;
import com.example.app_music.domain.Song;

public class Fragment_Lyrics extends Fragment {
    TextView textView;
    View view;
    private String lyrics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_lyrics,container,false);
        textView = view.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText(lyrics);
        return view;
    }

    public String setLyrics(String str){
        lyrics = str;
        return lyrics;
    }

}

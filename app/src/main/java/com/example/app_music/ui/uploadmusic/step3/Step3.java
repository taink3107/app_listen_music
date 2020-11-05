package com.example.app_music.ui.uploadmusic.step3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_music.R;
import com.example.app_music.adapter.SpinnerPlaylistAdapter;
import com.example.app_music.domain.Playlist;
import com.example.app_music.domain.Song;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Step3 extends Fragment {

    private Spinner spinner_playlist_song;
    private DatabaseReference dbReference;
    private List<Playlist> list;
    private Spinner spinner_theme_song;

    public static Step3 newInstance() {
        return new Step3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        View root = inflater.inflate(R.layout.step3_fragment, container, false);
        Song song = (Song) getArguments().getSerializable("new_song_step2");
        spinner_theme_song = root.findViewById(R.id.spinner_theme_song_upload);
        spinner_playlist_song = root.findViewById(R.id.spinner_playlist_song_upload);
        dbReference = FirebaseDatabase.getInstance().getReference("playlist");
        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Playlist p = new Playlist();
                    p.setId(dataSnapshot.getKey());
                    p.setName_playlist(String.valueOf(dataSnapshot.child("name_playlist").getValue()));
                    p.setImg_playlist_URL(String.valueOf(dataSnapshot.child("img_playlist_URL").getValue()));
                    list.add(p);
                }
                SpinnerPlaylistAdapter arrayAdapter = new SpinnerPlaylistAdapter(getContext(), android.R.layout.simple_spinner_item, list);
                spinner_playlist_song.setAdapter(arrayAdapter);
                spinner_theme_song.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), list.get(position).getId() + " ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.i("step3_newsong", song.toString());
        return root;
    }

}
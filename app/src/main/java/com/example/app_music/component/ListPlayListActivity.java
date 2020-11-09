package com.example.app_music.component;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_music.R;
import com.example.app_music.adapter.ListPlayListAdapter;
import com.example.app_music.domain.FireBaseHepler;
import com.example.app_music.domain.Playlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListPlayListActivity extends AppCompatActivity implements FireBaseHepler {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<Playlist> list;
    ListPlayListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_play_list);
        setUp();
        init();
        getData();
    }


    private void getData() {
        DATABASE_REFERENCE_PLAYLIST.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Playlist playlist = dataSnapshot.getValue(Playlist.class);
                    playlist.setId(dataSnapshot.getKey());
                    list.add(playlist);
                }

                adapter = new ListPlayListAdapter(ListPlayListActivity.this, list);
                recyclerView.setLayoutManager(new GridLayoutManager(ListPlayListActivity.this, 2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Play List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUp() {
        list = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar_list_playlist);
        recyclerView = findViewById(R.id.recycle_list_playlist);

    }
}
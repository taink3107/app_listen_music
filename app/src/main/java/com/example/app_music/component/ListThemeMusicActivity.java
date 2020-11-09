package com.example.app_music.component;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_music.R;
import com.example.app_music.adapter.ThemeMusicAdapter;
import com.example.app_music.domain.ThemeSong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListThemeMusicActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    ThemeMusicAdapter adapter;
    List<ThemeSong> themeSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_theme_music);
        setUp();
        getData();
    }

    private void setUp() {
        themeSongs = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_list_theme);
        toolbar = findViewById(R.id.toolbar_list_theme);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Theme Music");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("theme");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ThemeSong themeSong = dataSnapshot.getValue(ThemeSong.class);
                    themeSong.setId(dataSnapshot.getKey());
                    themeSongs.add(themeSong);
                }
                adapter = new ThemeMusicAdapter(themeSongs, ListThemeMusicActivity.this);
                recyclerView.setLayoutManager(new GridLayoutManager(ListThemeMusicActivity.this, 1));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
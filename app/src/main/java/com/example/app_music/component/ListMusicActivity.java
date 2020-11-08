package com.example.app_music.component;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_music.R;
import com.example.app_music.adapter.ListMusicAdapter;
import com.example.app_music.domain.Song;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListMusicActivity extends AppCompatActivity {
    List<Song> list;
    Song song;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView imageView;
    ListMusicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);
        init();
        list = new ArrayList<>();

        dataIntent();
        list.add(song);
        setValueInView(song.getSong_name(), song.getImage_URL());
        adapter = new ListMusicAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setValueInView(String name, String image) {
        collapsingToolbarLayout.setTitle(name);
        try {
            collapsingToolbarLayout.setBackground(new BitmapDrawable(getContentResolver().openInputStream(Uri.parse(image))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Picasso.get().load(image).fit().centerCrop().into(imageView);
    }

    private void init() {
        coordinatorLayout = findViewById(R.id.coording_layout_listmuic);
        collapsingToolbarLayout = findViewById(R.id.collaps_toolbar_listmusic);
        recyclerView = findViewById(R.id.recycle_list_music);
        floatingActionButton = findViewById(R.id.floating_action_button);
        toolbar = findViewById(R.id.toolbar_listmusic);
        imageView = findViewById(R.id.img_listmusic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);


    }

    private void dataIntent() {
        Intent intent = getIntent();
        song = (intent != null && intent.hasExtra("song")) ? (Song) intent.getSerializableExtra("song") : null;
        if (song == null) {
            Log.i("song_xyz", "song.getSong_name()");

        } else {
            Log.i("song_xyz", song.getSong_name());
        }
    }
}

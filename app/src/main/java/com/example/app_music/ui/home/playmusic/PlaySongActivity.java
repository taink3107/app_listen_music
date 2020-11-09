package com.example.app_music.ui.home.playmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.app_music.MusicMainActivity;
import com.example.app_music.R;
import com.example.app_music.adapter.ViewPagerPlaySong;
import com.example.app_music.domain.Playable;
import com.example.app_music.domain.Song;
import com.example.app_music.ui.Fragments.Fragment_Lyrics;
import com.example.app_music.ui.Fragments.Fragment_Play_Music_Bar;
import com.example.app_music.ui.Fragments.Fragment_Song_Disc;
import com.example.app_music.ui.Fragments.Fragment_Song_Info;
import com.example.app_music.ui.Notification.CreateNotification;
import com.example.app_music.ui.Services.OnClearFromRecentService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class PlaySongActivity extends AppCompatActivity implements Playable {

    DatabaseReference databaseReference;
    Toolbar toolbarPlaySong;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar sktTime;
    ImageButton imgPlay, imgRepeat, imgNext, imgPre, imgRandom;
    ViewPager viewPagerPlaySong;
    public static ViewPagerPlaySong adapterMusic;
    Fragment_Song_Disc fragment_song_disc;
    Fragment_Lyrics fragment_lyrics;
    Fragment_Song_Info fragment_song_info;
    MediaPlayer mediaPlayer;
    Song song = new Song();
    NotificationManager notificationManager;

    int position = 2;
    public static boolean isPlaying = false;
    public static boolean isPlaySongActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("music/test");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                song = dataSnapshot.getValue(Song.class);

                fragment_lyrics.setLyrics(song.getLyrics());

                Bundle bundle = new Bundle();
                bundle.putSerializable("songInfo", song);
                fragment_song_info.setArguments(bundle);

                adapterMusic = new ViewPagerPlaySong(getSupportFragmentManager());
                adapterMusic.addFragment(fragment_song_info);
                adapterMusic.addFragment(fragment_song_disc);
                adapterMusic.addFragment(fragment_lyrics);
                viewPagerPlaySong.setAdapter(adapterMusic);
                viewPagerPlaySong.setCurrentItem(1);

                playMusic();
                eventClick();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("error", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void CreateNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
            registerReceiver(receiver, new IntentFilter("TRACKS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
        }
        registerReceiver(receiver, new IntentFilter("EVENTS"));
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID, "noti", NotificationManager.IMPORTANCE_LOW);
            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (song != null) {
                    fragment_song_disc.setCircleImageView(song.getImage_URL());
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    onSongPause();
                } else {
                    onSongPlay();
                }
            }
        });
        sktTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void init() {
        toolbarPlaySong = findViewById(R.id.toolbarplaysong);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotalTimeSong = findViewById(R.id.textviewtotaltimesong);
        sktTime = findViewById(R.id.seekbarsong);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgPre = findViewById(R.id.imagebuttonpre);
        imgNext = findViewById(R.id.imagebuttonnext);
        imgRandom = findViewById(R.id.imagebuttonsuffle);
        imgRepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerPlaySong = findViewById(R.id.viewpagerplaysong);
        fragment_song_disc = new Fragment_Song_Disc();
        fragment_lyrics = new Fragment_Lyrics();
        fragment_song_info = new Fragment_Song_Info();
        setSupportActionBar(toolbarPlaySong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaySong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbarPlaySong.setTitleTextColor(Color.WHITE);

        Fragment_Play_Music_Bar fragment_play_music_bar = new Fragment_Play_Music_Bar();
        getSupportFragmentManager().beginTransaction().add(R.id.layoutplaymusicbar, fragment_play_music_bar).commit();

        CreateNotification();
        if (!isPlaySongActivity) {
            imgPlay = findViewById(R.id.imagebuttonplay);
            eventClick();
            isPlaySongActivity = true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlaySongActivity.this, MusicMainActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        isPlaySongActivity = false;
        CreateNotification();
        startActivity(intent);
    }

    private void playMusic() {
        fragment_song_disc = (Fragment_Song_Disc) adapterMusic.getItem(1);
        if (song != null) {
            fragment_song_disc.setCircleImageView(song.getImage_URL());
            getSupportActionBar().setTitle(song.getSong_name());
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(song.getMp3_URL());
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgPlay = findViewById(R.id.imagebuttonplay);
            imgPlay.setImageResource(R.drawable.iconpause);
            mediaPlayer.start();
            TimeSong();
            updateTime();

            CreateNotification.createNotification(PlaySongActivity.this, song, R.drawable.ic_baseline_pause_24, position, 1);
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktTime.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            onSongPause();
                        }
                    });
                }
            }
        }, 300);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getExtras().getString("actionname");
            switch (action) {
                case CreateNotification.ACTION_PRE:
                    onSongPre();
                    break;
                case CreateNotification.ACTION_PLAY:
                    if (isPlaying) {
                        onSongPause();
                    } else {
                        onSongPlay();
                    }
                    break;
                case CreateNotification.ACTION_NEXT:
                    onSongNext();
                    break;
            }
        }
    };

    @Override
    public void onSongPre() {
        position--;
        CreateNotification.createNotification(PlaySongActivity.this, song, R.drawable.ic_baseline_pause_24, position, 1);
    }

    @Override
    public void onSongPlay() {
        CreateNotification.createNotification(PlaySongActivity.this, song, R.drawable.ic_baseline_pause_24, position, 1);
        mediaPlayer.start();
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgPlay.setImageResource(R.drawable.iconpause);
        isPlaying = true;
    }

    @Override
    public void onSongPause() {
        CreateNotification.createNotification(PlaySongActivity.this, song, R.drawable.ic_baseline_play_arrow_24, position, 1);
        mediaPlayer.pause();
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgPlay.setImageResource(R.drawable.iconplay);
        isPlaying = false;
    }

    @Override
    public void onSongNext() {
        CreateNotification.createNotification(PlaySongActivity.this, song, R.drawable.ic_baseline_pause_24, position, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
package com.example.app_music.domain;

public interface Playable {
    void onSongPre();
    void onSongPlay();
    void onSongPause();
    void onSongNext();
}

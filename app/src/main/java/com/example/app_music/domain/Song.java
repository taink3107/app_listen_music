package com.example.app_music.domain;

import android.os.Bundle;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private String song_name;
    private List<String> singers;
    private String author;
    private String image_URL;
    private String mp3_URI;


    public Bundle putDataToBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("song_name",this.getSong_name());
        bundle.putString("author",this.getAuthor());
        bundle.putString("singers",this.getAuthor());
        return bundle;
    }
}

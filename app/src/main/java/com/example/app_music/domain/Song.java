package com.example.app_music.domain;

import android.net.Uri;
import android.os.Bundle;

import java.io.Serializable;
import java.util.Arrays;
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
public class Song implements Serializable {
    private String song_name;
    private String singers;
    private String image_URL;
    private String mp3_URL;
    private String type_song;
    private String theme_song;
    private String lyrics;
    private String content;

    public Bundle putDataToBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("song_name", this.getSong_name());
/*        bundle.putString("author", this.getAuthor());
        bundle.putString("singers", this.getAuthor());*/
        return bundle;
    }

//    public void processUri(String domain) {
//        String[] temp1 = domain.split("-");
//        this.setSong_name(temp1[0]);
//        if (temp1[1].contains("&")) {
//            this.setSingers(Arrays.asList(temp1[1].split("&")));
//        } else {
//            this.setSingers(Arrays.asList(temp1[1]));
//        }
//
//    }

 /*   public void processUri(String domain) {
        String[] temp1 = domain.split("-");
        this.setSong_name(temp1[0]);
        if (temp1[1].contains("&")) {
            this.setSingers(Arrays.asList(temp1[1].split("&")));
        } else {
            this.setSingers(Arrays.asList(temp1[1]));
        }*/

    @Override
    public String toString() {
        return "Song{" +
                "song_name='" + song_name + '\'' +
                ", singers='" + singers + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", mp3_URL='" + mp3_URL + '\'' +
                ", type_song='" + type_song + '\'' +
                ", theme_song='" + theme_song + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

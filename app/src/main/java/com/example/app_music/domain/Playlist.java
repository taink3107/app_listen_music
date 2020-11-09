package com.example.app_music.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playlist implements Serializable {
    private String id;
    private String name_playlist;
    private String img_playlist_URL;

}

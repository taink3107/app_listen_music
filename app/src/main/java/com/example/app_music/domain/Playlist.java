package com.example.app_music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playlist {
    private String id;
    private String name_playlist;
    private String img_playlist_URL;

}

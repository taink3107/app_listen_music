package com.example.app_music.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeSong {
    private String theme_name;
    private String img_theme_URL;
}

package com.example.music.app.DTO;

import lombok.Data;

@Data
public class SongResponseDTO {
    long id;
    String title;
    String artist;
    String album;
    int duration;
}

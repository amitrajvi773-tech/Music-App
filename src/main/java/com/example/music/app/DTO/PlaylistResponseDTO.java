package com.example.music.app.DTO;

import com.example.music.app.Entity.Favorite;
import com.example.music.app.Entity.Playlist;
import com.example.music.app.Entity.Song;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlaylistResponseDTO {
    long id;
    String name;
    List<Song> songs=new ArrayList<>();


}

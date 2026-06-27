package com.example.music.app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String name;

    @ManyToMany
     @JoinTable(name="playlist_song",
     joinColumns=@JoinColumn(name="playlist_id"),
     inverseJoinColumns =@JoinColumn(name="song_id"))
    List<Song> songs=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

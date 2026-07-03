package com.example.music.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(unique = true)
    private String title;

    private String artist;
    private String album;
    private int duration;

    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists=new ArrayList<>();

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    private List<Favorite> songFavorites = new ArrayList<>();





}

package com.example.music.app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;

   @ManyToOne
    @JoinColumn(name="song_id")
    private Song song;
}

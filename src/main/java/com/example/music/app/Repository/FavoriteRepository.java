package com.example.music.app.Repository;

import com.example.music.app.Entity.Favorite;
import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    Favorite findByUserAndSong(User user, Song song);

}

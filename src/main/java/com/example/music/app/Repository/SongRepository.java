package com.example.music.app.Repository;

import com.example.music.app.Entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Long> {
    Song findByTitle(String title);

    List<Song> findByTitleContainingIgnoreCaseAndArtistContainingIgnoreCase(String title, String artist);

    List<Song> findByTitleContainingIgnoreCase(String title);

    List<Song> findByArtistContainingIgnoreCase(String artist);
}

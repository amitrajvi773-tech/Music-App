package com.example.music.app.Repository;

import com.example.music.app.Entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
Song findByTitle(String title);


}

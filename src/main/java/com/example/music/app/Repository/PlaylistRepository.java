package com.example.music.app.Repository;

import com.example.music.app.Entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Playlist findByName(String name);
}

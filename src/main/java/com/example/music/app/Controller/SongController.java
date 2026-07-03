package com.example.music.app.Controller;

import com.example.music.app.Entity.Song;
import com.example.music.app.Service.SongService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public List<Song> getAllSong(){
    List<Song> song = songService.allSong();
    return song;
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        Song song = songService.findSongById(id);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("song/{myid}")
    public ResponseEntity<Void> delete(@PathVariable long myid){
        songService.deleteSong(myid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("song/{myid}")
    public Song update(@RequestBody Song song,@PathVariable long myid){
        Song existing=songService.findSongById(myid);

        existing.setTitle(song.getTitle());
        existing.setAlbum(song.getAlbum());
        existing.setArtist(song.getArtist());
        return songService.saveSong(existing);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam(required = false) String title,@RequestParam(required = false) String artist){
        return ResponseEntity.ok(songService.searchSongs(title,artist));
    }
}

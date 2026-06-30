package com.example.music.app.Controller;

import com.example.music.app.Entity.Song;
import com.example.music.app.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/allsong")
    public List<Song> getAllSong(){
    List<Song> song = songService.allSong();
    return song;
    }

    @GetMapping("song/{myid}")
    public ResponseEntity<Song> getSong(@PathVariable long myid){
         Song song= songService.findSongById(myid);
         return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping("song/{myid}")
    public void delete(@PathVariable long myid){
    songService.deleteSong(myid);
    }

    @PostMapping("song/{myid}")
    public Song update(@RequestBody Song song,@PathVariable long myid){
        Song existing=songService.findSongById(myid);
        if(existing==null){
            throw  new RuntimeException("song not found");
        }
        existing.setTitle(song.getTitle());
        existing.setTitle(song.getTitle());
        existing.setAlbum(song.getAlbum());
        existing.setArtist(song.getArtist());
        return existing;
    }
}

package com.example.music.app.Controller;

import com.example.music.app.DTO.SongResponseDTO;
import com.example.music.app.Entity.Song;
import com.example.music.app.Service.SongService;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@Tag(name = "Song Controller", description = "Manage Playlist")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    @Operation(summary = "get all song")
    public List<Song> getAllSong(){
    List<Song> song = songService.allSong();
    return song;
    }

    @GetMapping("/song/{id}")
    @Operation(summary = "get all song")
    public ResponseEntity<SongResponseDTO> getSong( @PathVariable Long id) {
        SongResponseDTO song = songService.findSongById(id);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("song/{myid}")
    @Operation(summary = "delete a song")
    public ResponseEntity<Void> delete(@PathVariable long myid){
        songService.deleteSong(myid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("song/{myid}")
    @Operation(summary = "update a song")
    public Song update(@RequestBody Song song,@PathVariable long myid){
        Song existing=songService.updatedSave(myid);

        existing.setTitle(song.getTitle());
        existing.setAlbum(song.getAlbum());
        existing.setArtist(song.getArtist());
        return songService.saveSong(existing);
    }

    @GetMapping("/search")
    @Operation(summary = "search  a song")
    public ResponseEntity<List<Song>> searchSong(@RequestParam(required = false) String title,@RequestParam(required = false) String artist){
        return ResponseEntity.ok(songService.searchSongs(title,artist));
    }

//    @GetMapping("/pagination")
//    public ResponseEntity<Page<Song>> getSongsByPageing(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size ){
//        return new ResponseEntity<>(songService.getSongsPage(page,size), HttpStatus.OK);
//    }

    @GetMapping("/songsorted")
    public ResponseEntity<Page<Song>> getSortedSong(@RequestParam(defaultValue = "0") int value,@RequestParam(defaultValue = "5") int size,@RequestParam(defaultValue = "title") String sortBy,@RequestParam(defaultValue = "asc") String direction){
        return ResponseEntity.ok(songService.getSongs(value,size,sortBy,direction));
    }
}

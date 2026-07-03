package com.example.music.app.Controller;

import com.example.music.app.Entity.Playlist;
import com.example.music.app.Service.PlaylistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public List<Playlist> getPlaylists(){

        List<Playlist>  playlist=playlistService.getAllPlaylist();
        return playlist;
    }

    @GetMapping("playlist/{myid}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable long myid){

        Playlist play=playlistService.getById(myid);
        if(play==null){
            throw  new EntityNotFoundException("playlist entity not found");
        }
        return ResponseEntity.ok().body(play);
    }

    @PostMapping("addPlaylist")
    public ResponseEntity<Playlist> addPlaylist(@RequestBody Playlist playlist){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

       Playlist saved= playlistService.saveNewPlaylist(playlist,username);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> addSongInPlaylist(  @PathVariable Long playlistId, @PathVariable Long songId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
     playlistService.addSongToPlaylist(playlistId,songId,username);
     return ResponseEntity.ok("song add succesfully");
    }

    @DeleteMapping("/playlist/{myid}")
    public ResponseEntity<?> deletePl(@PathVariable long myid){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        playlistService.deletePlaylist(myid,username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{playlistId}/song/{songId}")
    public ResponseEntity<?> removeSongInPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        playlistService.removeSongFromPlaylist(playlistId, songId,username);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/playlists/{myid}")
    public ResponseEntity<Playlist> update(@RequestBody Playlist playlist, @PathVariable long myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Playlist pl=playlistService.getById(myid);
        pl.setName(playlist.getName());
       return new ResponseEntity<>(playlistService.savePlaylist(pl,username), HttpStatus.OK);


    }

}

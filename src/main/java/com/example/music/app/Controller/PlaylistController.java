package com.example.music.app.Controller;

import com.example.music.app.Entity.Playlist;
import com.example.music.app.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists")
    public List<Playlist> getPlaylists(){

        List<Playlist>  playlist=playlistService.getAllPlaylist();
        return playlist;
    }

    @GetMapping("playlist/{myid}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable long myid){

        Playlist play=playlistService.getById(myid);
        return ResponseEntity.ok().body(play);
    }

    @PostMapping("addPlaylist")
    public ResponseEntity<Playlist> addPlaylist(@RequestBody Playlist playlist){
       Playlist play= playlistService.savePlaylist(playlist);
       return ResponseEntity.ok().body(play);
    }

    @DeleteMapping("playlist/{myid}")
    public void deletePl(@PathVariable long myid){
        playlistService.deletePlaylist(myid);
    }

    @PutMapping("update/{myid}")
    public ResponseEntity<Playlist> update(@RequestBody Playlist playlist, @PathVariable long myid){
       Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
       Playlist pl=playlistService.getById(myid);
       pl.setName(playlist.getName());
       return new ResponseEntity<>(playlistService.savePlaylist(pl), HttpStatus.OK);


    }

}

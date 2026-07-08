package com.example.music.app.Controller;

import com.example.music.app.DTO.PlaylistResponseDTO;
import com.example.music.app.Entity.Playlist;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.example.music.app.Service.PlaylistService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@Tag(name = "Playlist Controller", description = "Manage playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    @Operation(summary = "get all playlist")
    public List<Playlist> getPlaylists(){

        List<Playlist>  playlist=playlistService.getAllPlaylist();
        return playlist;
    }

    @GetMapping("playlist/{myid}")
    @Operation(summary = "get song by playlist")
    public ResponseEntity<PlaylistResponseDTO> getPlaylist(@PathVariable long myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        PlaylistResponseDTO play=playlistService.getById(myid,username);
        if(play==null){
            throw  new EntityNotFoundException("playlist entity not found");
        }
        return ResponseEntity.ok().body(play);
    }

    @PostMapping("addPlaylist")
    @Operation(summary = "post playlist ")
    public ResponseEntity<Playlist> addPlaylist(@Valid @RequestBody Playlist playlist){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

       Playlist saved= playlistService.saveNewPlaylist(playlist,username);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    @Operation(summary = "post song by id ")
    public ResponseEntity<?> addSongInPlaylist(@Valid  @PathVariable Long playlistId, @PathVariable Long songId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
     playlistService.addSongToPlaylist(playlistId,songId,username);
     return ResponseEntity.ok("song add succesfully");
    }

    @DeleteMapping("/playlist/{myid}")
    @Operation(summary = "post playlist ")
    public ResponseEntity<?> deletePl(@PathVariable long myid){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        playlistService.deletePlaylist(myid,username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{playlistId}/song/{songId}")
    @Operation(summary = "delete playlist ")
    public ResponseEntity<?> removeSongInPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        playlistService.removeSongFromPlaylist(playlistId, songId,username);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/playlists/{myid}")
    @Operation(summary = "update  playlist ")
    public ResponseEntity<Playlist> update(@RequestBody Playlist playlist, @PathVariable long myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Playlist pl=playlistService.updateById(myid);
        pl.setName(playlist.getName());
       return new ResponseEntity<>(playlistService.savePlaylist(pl,username), HttpStatus.OK);


    }

}

package com.example.music.app.Controller;

import com.example.music.app.DTO.FavoriteResponseDTO;
import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import com.example.music.app.Repository.FavoriteRepository;
import com.example.music.app.Repository.SongRepository;
import com.example.music.app.Service.FavoriteService;
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
@RequestMapping("/favorites")
public class FavoriteController {

 @Autowired
 private SongRepository songRepository;

 @Autowired
 private FavoriteService favoriteService;
 @PostMapping("/{songid}")
  public ResponseEntity<?> addFavorite(@Valid @PathVariable long songid){
     Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
     String username=authentication.getName();
     Song song=songRepository.findById(songid).orElseThrow(()->new EntityNotFoundException("song not in favorite "));
     favoriteService.songFavorites(song,username);
     return ResponseEntity.ok("favoruote song added");
 }

 @GetMapping
    public ResponseEntity<List<FavoriteResponseDTO>> getFavorite(){
     Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
     String username=authentication.getName();
     return ResponseEntity.ok(favoriteService.getFavorites(username));
 }

 @DeleteMapping("/{songid}")
    public ResponseEntity<?> deletefavorite(@PathVariable long songid){
     Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
     String username= authentication.getName();
     Song song=songRepository.findById(songid).orElseThrow(()-> new EntityNotFoundException("songid not found"));
     favoriteService.deleteFavorite(song,username);
     return ResponseEntity.noContent().build();
 }
}

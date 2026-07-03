package com.example.music.app.Controller;

import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import com.example.music.app.Service.SongService;
import com.example.music.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SongService songService;

    @PostMapping("/song")
     public ResponseEntity<Song> addSong(@RequestBody Song song) {

     Song saved = songService.saveSong(song);

    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
}}

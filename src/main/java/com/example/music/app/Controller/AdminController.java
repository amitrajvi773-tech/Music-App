package com.example.music.app.Controller;

import com.example.music.app.DTO.UserResponseDTO;
import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import com.example.music.app.Service.SongService;
import com.example.music.app.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @PostMapping("/song")
    public ResponseEntity<Song> addSong(
            @RequestParam String title,
            @RequestParam String artist,
            @RequestParam(required = false) String album,
            @RequestParam int duration,
            @RequestParam MultipartFile audioFile,
            @RequestParam(required = false) MultipartFile imageFile) throws IOException {

        Song saved = songService.saveSong(
                title,
                artist,
                album,
                duration,
                audioFile,
                imageFile
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("alluser")
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
        List<UserResponseDTO> user=userService.AllUser();
        return ResponseEntity.ok().body(user);
    }
}


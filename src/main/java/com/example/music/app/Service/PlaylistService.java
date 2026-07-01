package com.example.music.app.Service;

import com.example.music.app.Entity.Playlist;
import com.example.music.app.Repository.PlaylistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> getAllPlaylist(){
       return playlistRepository.findAll();
    }

    public Playlist getById(long myid){
        return playlistRepository.findById(myid).orElseThrow(()-> new UsernameNotFoundException("Playlist not found"));
    }
    public Playlist savePlaylist(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(long id){
        playlistRepository.deleteById(id);
    }


    public Playlist findByname(String name){
      return   playlistRepository.findByName(name);
    }
    }


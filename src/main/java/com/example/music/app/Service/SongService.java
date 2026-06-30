package com.example.music.app.Service;

import com.example.music.app.Entity.Song;
import com.example.music.app.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> allSong() {
        return songRepository.findAll();

    }


    public Song findSongById(long myid) {

        return   songRepository.findById(myid);
    }

    public void deleteSong(long id){
        songRepository.deleteById(id);
    }

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

}

package com.example.music.app.Service;

import com.example.music.app.Entity.Song;
import com.example.music.app.Repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> allSong() {
        return songRepository.findAll();

    }


    public Song findSongById(long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Song not found"));
    }

    public void deleteSong(long id){

        songRepository.deleteById(id);
    }

    public Song saveSong(Song song) {

        return songRepository.save(song);
    }

    public List<Song> searchSongs(String title, String artist) {
    if(title != null && artist != null){
     return songRepository.findByTitleContainingIgnoreCaseAndArtistContainingIgnoreCase(title,artist);
     }
     if(title!=null){
      return songRepository.findByTitleContainingIgnoreCase(title);
     }
     if(artist!=null){
         return songRepository.findByArtistContainingIgnoreCase(artist);
     }
     return songRepository.findAll();
    }
}

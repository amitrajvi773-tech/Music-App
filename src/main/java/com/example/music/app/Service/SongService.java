package com.example.music.app.Service;

import com.example.music.app.DTO.SongResponseDTO;
import com.example.music.app.Entity.Song;
import com.example.music.app.Repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.processing.Generated;
import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> allSong() {
        return songRepository.findAll();

    }


    public SongResponseDTO findSongById(long id) {
        Song song= songRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("entity not found "));
        SongResponseDTO dto = new SongResponseDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setArtist(song.getArtist());
        dto.setAlbum(song.getAlbum());
        dto.setDuration(song.getDuration());

        return dto;
    }

    public Song updatedSave(long id){
        Song song=songRepository.findById(id).orElseThrow(()->new EntityNotFoundException("entity not found "));
        return songRepository.save(song);
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

    public Page<Song> getSongsPage(int page,int size,String sortBy){
        Pageable pageable= PageRequest.of(page,size,Sort.by(sortBy));
        return songRepository.findAll(pageable);
    }

    public Page<Song> getSortedSongs(int page,int size,String sortBy){

    }

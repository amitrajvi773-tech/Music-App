package com.example.music.app.Service;

import com.example.music.app.DTO.SongResponseDTO;
import com.example.music.app.Entity.Song;
import com.example.music.app.Repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.Generated;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> allSong() {
        return songRepository.findAll();

    }


    public SongResponseDTO findSongById(long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("entity not found "));
        SongResponseDTO dto = new SongResponseDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setArtist(song.getArtist());
        dto.setAlbum(song.getAlbum());
        dto.setDuration(song.getDuration());

        return dto;
    }

    public Song updatedSave(Song existing) {
        return songRepository.save(existing);
    }

    public void deleteSong(long id) {

        songRepository.deleteById(id);
    }

    public Song saveSong(String title, String artist, String album, int duration, MultipartFile audioFile, MultipartFile imageFile) throws IOException {

        String audioFileName = audioFile.getOriginalFilename();
        Path audioPath = Paths.get("uploads/songs", audioFileName);
        Files.copy(audioFile.getInputStream(), audioPath);

        String imageFileName = imageFile.getOriginalFilename();
        Path imagePath = Paths.get("uploads/images", imageFileName);
        Files.copy(imageFile.getInputStream(), imagePath);

        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setAlbum(album);
        song.setDuration(duration);
        song.setAudioPath(audioPath.toString());
        song.setImagePath(imagePath.toString());

        return songRepository.save(song);
    }
    public List<Song> searchSongs(String title, String artist) {
        if (title != null && artist != null) {
            return songRepository.findByTitleContainingIgnoreCaseAndArtistContainingIgnoreCase(title, artist);
        }
        if (title != null) {
            return songRepository.findByTitleContainingIgnoreCase(title);
        }
        if (artist != null) {
            return songRepository.findByArtistContainingIgnoreCase(artist);
        }
        return songRepository.findAll();
    }



    public Page<Song> getSongs(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return songRepository.findAll(pageable);
    }


}
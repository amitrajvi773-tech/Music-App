package com.example.music.app.Service;

import com.example.music.app.DTO.PlaylistResponseDTO;
import com.example.music.app.Entity.Playlist;
import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import com.example.music.app.Repository.PlaylistRepository;
import com.example.music.app.Repository.SongRepository;
import com.example.music.app.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    public List<Playlist> getAllPlaylist(){
       return playlistRepository.findAll();
    }

    public PlaylistResponseDTO getById(long myid){
        Playlist play= playlistRepository.findById(myid).orElseThrow(()-> new UsernameNotFoundException("Playlist not found"));
        PlaylistResponseDTO dto=new PlaylistResponseDTO();
        dto.setId(play.getId());
        dto.setName(play.getName());
        dto.setSongs(play.getSongs());
        return dto;
    }

    public Playlist updateById(long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found"));
    }
    public Playlist saveNewPlaylist(Playlist playlist, String username){
        User user=userRepository.findByUsername(username);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    public Playlist savePlaylist(Playlist playlist,String username){
        if(!playlist.getUser().getUsername().equals(username)){
            throw new UsernameNotFoundException("user not is not valid ");
        }
        return playlistRepository.save(playlist);

    }


    public void deletePlaylist(long id,String username){
 Playlist playlist=playlistRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("playlist not found"));
 if(!playlist.getUser().getUsername().equals(username)){
     throw new UsernameNotFoundException("username not found ");
 }
        playlistRepository.deleteById(playlist.getId());
    }


    public Playlist findByname(String name){

        return   playlistRepository.findByName(name);
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId,String username) {
        Playlist playlist=playlistRepository.findById(playlistId).orElseThrow(()-> new EntityNotFoundException("Playlist not found"));
        if(!playlist.getUser().getUsername().equals(username)){
            throw new UsernameNotFoundException("username not found ");
        }
        Song song=songRepository.findById(songId).orElseThrow(()-> new  UsernameNotFoundException("song not found "));

        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);
    }

    public void addSongToPlaylist(Long playlistId, Long songId, String username) {
        Playlist playlist=playlistRepository.findById(playlistId).orElseThrow(()-> new EntityNotFoundException("Playlist not found"));
        if(!playlist.getUser().getUsername().equals(username)){
            throw new UsernameNotFoundException("username not found ");
        }
        Song song=songRepository.findById(songId).orElseThrow(()-> new UsernameNotFoundException(" Song not found "));
        if (playlist.getSongs().contains(song)) {
            throw new IllegalArgumentException("Song already exists in playlist");
        }
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);

    }
}


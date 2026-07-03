package com.example.music.app.Service;

import com.example.music.app.Entity.Favorite;
import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import com.example.music.app.Repository.FavoriteRepository;
import com.example.music.app.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
@Autowired
    private UserRepository userRepository;
@Autowired
private FavoriteRepository favoriteRepository;

    public void songFavorites(Song song, String username) {
        User user=userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Favorite existing = favoriteRepository.findByUserAndSong(user, song);
        if ( existing!= null) {
            throw new IllegalArgumentException("Song already in favorites");
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setSong(song);

        favoriteRepository.save(favorite);

    }


    public List<Favorite> getFavorites(String username) {
        User user=userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Favorite> liked=user.getFavorites();
        return liked;

    }

    public void deleteFavorite(Song song, String username) {
     User user=userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
     Favorite favorite=favoriteRepository.findByUserAndSong(user,song);
        if (favorite == null) {
            throw new EntityNotFoundException("Favorite not found");
        }

        favoriteRepository.delete(favorite);
    }
}

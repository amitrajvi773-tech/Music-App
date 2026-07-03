package com.example.music.app.Service;

import com.example.music.app.Entity.Favorite;
import com.example.music.app.Entity.Song;
import com.example.music.app.Entity.User;
import com.example.music.app.Repository.FavoriteRepository;
import com.example.music.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Favorite favorite=new Favorite();
        favorite.setSong(song);
        favorite.setUser(user);
        favoriteRepository.save(favorite);

    }


    public List<Favorite> getFavorites(String username) {
        User user=userRepository.findByUsername(username);
        List<Favorite> liked=user.getFavorites();
        return liked;

    }

    public void deleteFavorite(long songid, String username) {

    }
}

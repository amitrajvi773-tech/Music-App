package com.example.music.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> userrole=new ArrayList<>();


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<>();
}

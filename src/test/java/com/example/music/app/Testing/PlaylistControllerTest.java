package com.example.music.app.Testing;

import com.example.music.app.Controller.PlaylistController;
import com.example.music.app.JWT.JwtFilter;
import com.example.music.app.JWT.JwtUtilis;
import com.example.music.app.Service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaylistController.class)
@AutoConfigureMockMvc(addFilters = false)

public class PlaylistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PlaylistService playlistService ;

    @MockitoBean
    JwtFilter jwtFilter;

    @MockitoBean
    JwtUtilis jwtUtilis;

    @Test
    @WithMockUser(username = "amit")
    void testGetPlaylistNotFound() throws Exception {
        long id = 1;

        when(playlistService.getById(id, "amit")).thenReturn(null);
        mockMvc.perform(get("/playlists/playlist/{myid}", id))
                .andExpect(status().isNotFound());

        verify(playlistService).getById(id, "amit");
    }
}

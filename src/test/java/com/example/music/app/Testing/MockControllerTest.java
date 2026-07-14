package com.example.music.app.Testing;

import com.example.music.app.Controller.SongController;
import com.example.music.app.DTO.SongResponseDTO;
import com.example.music.app.Repository.SongRepository;
import com.example.music.app.Service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.example.music.app.JWT.JwtUtilis;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

@WebMvcTest(SongController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MockControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    SongService songService;

    @MockitoBean
    SongRepository songRepository;

    @MockitoBean
    JwtUtilis jwtUtilis;

    @MockitoBean
    UserDetailsService userDetailsService;

    @Test
    void testgetAllSong () throws Exception {
        SongResponseDTO dto=new SongResponseDTO();
        dto.setTitle("beliver");
        dto.setArtist("edsheeren");
        dto.setDuration(23);
        dto.setId(3);

        when(songService.allSong()).thenReturn(List.of(dto));

        mockMvc.perform(get("/song/allsongs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].title").value("beliver"))
                .andExpect(jsonPath("$[0].artist").value("edsheeren"));
    }

    @Test
    void testDelete() throws Exception{
        long id=1;


                mockMvc.perform(delete("/song/song/{myid}",id))
                        .andExpect((status().isNoContent()))
                        .andExpect(content().string("song is delted"));
    }

}

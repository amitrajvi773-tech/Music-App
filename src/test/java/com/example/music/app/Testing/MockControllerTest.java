package com.example.music.app.Testing;

import com.example.music.app.Controller.SongController;
import com.example.music.app.Repository.SongRepository;
import com.example.music.app.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SongController.class)
public class MockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SongService songService;

    @MockitoBean
    private SongRepository songRepository;

}

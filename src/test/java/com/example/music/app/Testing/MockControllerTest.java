package com.example.music.app.Testing;

import com.example.music.app.Controller.SongController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebMvcTest(SongController.class)
public class MockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @MockBean
    private SongRepository songRepository;

}

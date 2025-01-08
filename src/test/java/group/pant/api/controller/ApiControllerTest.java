package group.pant.api.controller;

import group.pant.api.model.Cuisine;
import group.pant.api.service.CuisineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiControllerTest {

    @Mock
    private CuisineService cuisineService;

    @InjectMocks
    private ApiController apiController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    public void shouldGetAllCuisines() throws Exception {
        // Given
        Cuisine cuisine1 = new Cuisine();
        cuisine1.setId(1);
        cuisine1.setNom("Italian");

        Cuisine cuisine2 = new Cuisine();
        cuisine2.setId(2);
        cuisine2.setNom("Chinese");

        List<Cuisine> cuisines = Arrays.asList(cuisine1, cuisine2);

        when(cuisineService.getAllCuisines()).thenReturn(cuisines);

        // When & Then
        mockMvc.perform(get("/api/cuisines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nom").value("Italian"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nom").value("Chinese"));
    }
}

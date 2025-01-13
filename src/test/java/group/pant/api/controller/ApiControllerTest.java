package group.pant.api.controller;

import group.pant.api.model.Cuisine;
import group.pant.api.service.CuisineService;
import group.pant.api.service.PlatService;
import group.pant.api.service.RestaurantService;
import group.pant.api.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UtilisateurService utilisateurService;

    @MockitoBean
    private PlatService platService;

    @MockitoBean
    private RestaurantService restaurantService;

    @MockitoBean
    private CuisineService cuisineService;

    @Test
    public void givenCuisines_whenGetCuisines_thenReturnJsonArray() throws Exception {
        Cuisine c1 = new Cuisine();
        c1.setId(1);
        c1.setNom("Française");

        Cuisine c2 = new Cuisine();
        c2.setId(2);
        c2.setNom("Japonaise");

        List<Cuisine> allCuisines = Arrays.asList(c1, c2);

        given(cuisineService.getAllCuisines()).willReturn(allCuisines);

        mvc.perform(get("/api/cuisines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nom", is(c1.getNom())));
    }
}
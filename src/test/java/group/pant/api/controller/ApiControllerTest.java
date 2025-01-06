package group.pant.api.controller;

import group.pant.api.service.CuisineService;
import group.pant.api.service.PlatService;
import group.pant.api.service.RestaurantService;
import group.pant.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CuisineService cuisineService;
    @MockitoBean
    private PlatService platService;
    @MockitoBean
    private RestaurantService restaurantService;
    @MockitoBean
    private UtilisateurService utilisateurService;



}

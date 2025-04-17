package group.pant.api.controller;

import group.pant.api.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiControllerSystemTest {

    @PersistenceContext
    private EntityManager entityManager; // Using jakarta.persistence

    @Autowired
    private MockMvc mockMvc;

    private static final String API_BASE = "/api";

    @Transactional
    @Nested
    class CuisineTests {
        private static final String CUISINES_ENDPOINT = API_BASE + "/cuisines";

        private Integer firstCuisineId;
        private String cuisinesEndpointId;

        @BeforeEach
        void setupCuisines() {
            String[] cuisineNames = {
                    "Française", "Italienne", "Japonaise",
                    "Mexicaine", "Américaine", "Chinoise",
                    "Indienne", "Espagnole"
            };

            for (String name : cuisineNames) {
                Cuisine cuisine = new Cuisine();
                cuisine.setNom(name);
                entityManager.persist(cuisine);

                if (firstCuisineId == null) {
                    firstCuisineId = cuisine.getId();
                    cuisinesEndpointId = CUISINES_ENDPOINT + "/" + firstCuisineId;
                }
            }
            entityManager.flush();
        }

        @Test
        void testGetAllCuisines() throws Exception {
            mockMvc.perform(get(CUISINES_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(8))
                    .andExpect(jsonPath("$[0].nom").value("Française"))
                    .andExpect(jsonPath("$[1].nom").value("Italienne"))
                    .andExpect(jsonPath("$[2].nom").value("Japonaise"))
                    .andExpect(jsonPath("$[3].nom").value("Mexicaine"))
                    .andExpect(jsonPath("$[4].nom").value("Américaine"))
                    .andExpect(jsonPath("$[5].nom").value("Chinoise"))
                    .andExpect(jsonPath("$[6].nom").value("Indienne"))
                    .andExpect(jsonPath("$[7].nom").value("Espagnole"));
        }

        @Test
        void testGetCuisineById() throws Exception {
            mockMvc.perform(get(cuisinesEndpointId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(firstCuisineId))
                    .andExpect(jsonPath("$.nom").value("Française")); // First cuisine name
        }

        @Test
        void testAddCuisine() throws Exception {
            String newCuisine = "{\"nom\": \"Mexican\"}";

            mockMvc.perform(post(CUISINES_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newCuisine))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nom").value("Mexican"));
        }

        @Test
        void testDeleteCuisine() throws Exception {
            mockMvc.perform(delete(cuisinesEndpointId)) // Use firstCuisineId
                    .andExpect(status().isOk())
                    .andExpect(content().string("Cuisine with id " + firstCuisineId + " deleted")); // Dynamic ID
        }

        @Test
        void testUpdateCuisine() throws Exception {
            String updatedCuisine = "{\"nom\": \"Updated Cuisine\"}";

            mockMvc.perform(put(cuisinesEndpointId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updatedCuisine))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nom").value("Updated Cuisine"));
        }

        @Test
        void testPatchCuisine() throws Exception {
            String patchData = "{\"nom\": \"Patched Cuisine\"}";

            mockMvc.perform(patch(cuisinesEndpointId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(patchData))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nom").value("Patched Cuisine"));
        }
    }
    @Nested
    @Transactional
    class RestaurantTests {
        private static final String RESTAURANTS_ENDPOINT = API_BASE + "/restaurants";

        private Integer firstRestaurantId;
        private String restaurantsEndpointId;

        @BeforeEach
        void setupRestaurants() {
            Role role = new Role();
            role.setNom("ROLE_USER");
            entityManager.persist(role);

            Ville ville = new Ville();
            ville.setNom("Springfield");
            ville.setCodePostal("12345");
            entityManager.persist(ville);

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setPrenom("John");
            utilisateur.setNom("Doe");
            utilisateur.setTelMobile("0123456789");
            utilisateur.setTelFix("0987654321");
            utilisateur.setMail("john.doe@example.com");
            utilisateur.setParametre("{}");
            utilisateur.setPointsFidelite(0);
            utilisateur.setIdRole(role);
            entityManager.persist(utilisateur);

            Adresse adresse = new Adresse();
            adresse.setNumero("123");
            adresse.setRue("Main St");
            adresse.setComplement("Apt 4B");
            adresse.setLongitude(1.2345);
            adresse.setLatitude(5.6789);
            adresse.setIdVille(ville);
            entityManager.persist(adresse);

            String[] restaurantNames = {
                    "Le Gourmet", "Pizzeria Bella", "Sushi House",
                    "Taco Fiesta", "American Diner", "Chinois Express",
                    "Curry Palace", "Spanish Tapas Bar"
            };

            for (String name : restaurantNames) {
                Restaurant restaurant = new Restaurant();
                restaurant.setNom(name);
                restaurant.setTelephone("123456789");
                restaurant.setCapacite(50);
                restaurant.setPhoto("default_photo.jpg");

                restaurant.setIdAdresse(adresse);
                restaurant.setIdRestaurateur(utilisateur);

                entityManager.persist(restaurant);

                if (firstRestaurantId == null) {
                    firstRestaurantId = restaurant.getId();
                    restaurantsEndpointId = RESTAURANTS_ENDPOINT + "/" + firstRestaurantId;
                }
            }
            entityManager.flush();
        }

        @Test
        void testGetAllRestaurants() throws Exception {
            mockMvc.perform(get(RESTAURANTS_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(8))
                    .andExpect(jsonPath("$[0].nom").value("Le Gourmet"))
                    .andExpect(jsonPath("$[1].nom").value("Pizzeria Bella"))
                    .andExpect(jsonPath("$[2].nom").value("Sushi House"))
                    .andExpect(jsonPath("$[3].nom").value("Taco Fiesta"))
                    .andExpect(jsonPath("$[4].nom").value("American Diner"))
                    .andExpect(jsonPath("$[5].nom").value("Chinois Express"))
                    .andExpect(jsonPath("$[6].nom").value("Curry Palace"))
                    .andExpect(jsonPath("$[7].nom").value("Spanish Tapas Bar"));
        }

        @Test
        void testGetRestaurantById() throws Exception {
            mockMvc.perform(get(restaurantsEndpointId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(firstRestaurantId))
                    .andExpect(jsonPath("$.nom").value("Le Gourmet"));
        }

        @Test
        void testAddRestaurant() throws Exception {
            Integer adresseId = entityManager.createQuery("SELECT a.id FROM Adresse a", Integer.class)
                    .setMaxResults(1)
                    .getSingleResult();

            Integer utilisateurId = entityManager.createQuery("SELECT u.id FROM Utilisateur u", Integer.class)
                    .setMaxResults(1)
                    .getSingleResult();

            String newRestaurant = "{\"nom\": \"New Restaurant\", \"telephone\": \"987654321\", \"capacite\": 60, \"photo\": \"new_photo.jpg\", \"idAdresse\": {\"id\": " + adresseId + "}, \"idRestaurateur\": {\"id\": " + utilisateurId + "}}";

            mockMvc.perform(post(RESTAURANTS_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newRestaurant))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nom").value("New Restaurant"));
        }

        @Test
        void testDeleteRestaurant() throws Exception {
            mockMvc.perform(delete(restaurantsEndpointId))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Restaurant with id " + firstRestaurantId + " deleted"));
        }

        @Test
        void testUpdateRestaurant() throws Exception {
            String updatedRestaurant = "{\"nom\": \"Updated Restaurant\", \"telephone\": \"111111111\", \"capacite\": 70, \"photo\": \"updated_photo.jpg\", \"idAdresse\": {\"id\": 1}, \"idRestaurateur\": {\"id\": 1}}";

            mockMvc.perform(put(restaurantsEndpointId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updatedRestaurant))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nom").value("Updated Restaurant"));
        }

        @Test
        void testPatchRestaurant() throws Exception {
            String patchData = "{\"nom\": \"Patched Restaurant\"}";

            mockMvc.perform(patch(restaurantsEndpointId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(patchData))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nom").value("Patched Restaurant"));
        }
    }
}
package group.pant.api.controller;

import group.pant.api.model.Cuisine;
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
            // Clear existing cuisines if needed
            entityManager.createQuery("DELETE FROM Cuisine").executeUpdate();

            // Create and persist cuisine data directly using EntityManager
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
}
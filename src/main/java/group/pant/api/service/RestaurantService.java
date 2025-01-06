package group.pant.api.service;

import group.pant.api.model.Restaurant;
import group.pant.api.model.Adresse;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.AdresseRepository;
import group.pant.api.repository.RestaurantRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AdresseRepository adresseRepository;
    private final UtilisateurRepository utilisateurRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(int id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + id + " not found"));
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public String deleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
        return "Deleted Restaurant";
    }

    public Restaurant updateRestaurant(int id, Restaurant restaurant) {
        restaurant.setId(id);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant patchRestaurant(int id, Map<String, Object> patch) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingRestaurant.setNom((String) value);
                    break;
                case "telephone":
                    existingRestaurant.setTelephone((String) value);
                    break;
                case "capacite":
                    existingRestaurant.setCapacite((Integer) value);
                    break;
                case "photo":
                    existingRestaurant.setPhoto((String) value);
                    break;
                case "dateCreation":
                    existingRestaurant.setDateCreation(Instant.parse((String) value));
                    break;
                case "idAdresse":
                    if (value instanceof Map<?, ?> addressMap) {
                        Object addressIdValue = addressMap.get("id");
                        if (addressIdValue instanceof Integer addressId) { // Check if it is an Integer
                            Adresse adresse = adresseRepository.findById(addressId)
                                    .orElseThrow(() -> new RuntimeException("Adresse not found"));
                            existingRestaurant.setIdAdresse(adresse);
                        } else {
                            throw new IllegalArgumentException("Invalid address ID type");
                        }
                    }
                    break;
                case "idRestaurateur":
                    if (value instanceof Map<?, ?> userMap) {
                        Object userIdValue = userMap.get("id");
                        if (userIdValue instanceof Integer userId) { // Check if it is an Integer
                            Utilisateur restaurateur = utilisateurRepository.findById(userId)
                                    .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
                            existingRestaurant.setIdRestaurateur(restaurateur);
                        } else {
                            throw new IllegalArgumentException("Invalid restaurateur ID type");
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown attribute: " + key);
            }
        });

        return restaurantRepository.save(existingRestaurant);
    }

}

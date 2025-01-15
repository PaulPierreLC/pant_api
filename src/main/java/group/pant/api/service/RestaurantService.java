package group.pant.api.service;

import group.pant.api.model.Adresse;
import group.pant.api.model.Restaurant;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.AdresseRepository;
import group.pant.api.repository.RestaurantRepository;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant updateRestaurant(int id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + id + " not found"));

        existingRestaurant.setNom(restaurant.getNom());
        existingRestaurant.setTelephone(restaurant.getTelephone());
        existingRestaurant.setCapacite(restaurant.getCapacite());
        existingRestaurant.setPhoto(restaurant.getPhoto());
        existingRestaurant.setIdAdresse(restaurant.getIdAdresse());
        existingRestaurant.setIdRestaurateur(restaurant.getIdRestaurateur());

        return restaurantRepository.save(existingRestaurant);
    }

    public Restaurant patchRestaurant(int id, Map<String, Object> patch) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + id + " not found"));

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
                case "idAdresse":
                    if (value instanceof Map<?, ?> adresseMap) {
                        Integer adresseId = (Integer) adresseMap.get("id");
                        Adresse adresse = adresseRepository.findById(adresseId)
                                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + adresseId + " not found"));
                        existingRestaurant.setIdAdresse(adresse);
                    }
                    break;
                case "idRestaurateur":
                    if (value instanceof Map<?, ?> utilisateurMap) {
                        Integer utilisateurId = (Integer) utilisateurMap.get("id");
                        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                                .orElseThrow(() -> new EntityNotFoundException("Utilisateur with id " + utilisateurId + " not found"));
                        existingRestaurant.setIdRestaurateur(utilisateur);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return restaurantRepository.save(existingRestaurant);
    }
}

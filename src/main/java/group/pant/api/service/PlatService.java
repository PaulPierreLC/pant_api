package group.pant.api.service;

import group.pant.api.model.Plat;
import group.pant.api.model.Restaurant;
import group.pant.api.repository.PlatRepository;
import group.pant.api.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlatService {
    private final PlatRepository platRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Plat getPlatById(int id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + id + " not found"));
    }

    public Plat addPlat(Plat plat) {
        return platRepository.save(plat);
    }

    public void deletePlat(int id) {
        platRepository.deleteById(id);
    }

    public Plat updatePlat(int id, Plat plat) {
        Plat existingPlat = platRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + id + " not found"));

        existingPlat.setNom(plat.getNom());
        existingPlat.setDescription(plat.getDescription());
        existingPlat.setPrix(plat.getPrix());
        existingPlat.setPoids(plat.getPoids());
        existingPlat.setStock(plat.getStock());
        existingPlat.setPhoto(plat.getPhoto());
        existingPlat.setIdRestaurant(plat.getIdRestaurant());

        return platRepository.save(existingPlat);
    }

    public Plat patchPlat(int id, Map<String, Object> patch) {
        Plat existingPlat = platRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingPlat.setNom((String) value);
                    break;
                case "description":
                    existingPlat.setDescription((String) value);
                    break;
                case "prix":
                    existingPlat.setPrix((Float) value);
                    break;
                case "poids":
                    existingPlat.setPoids((Float) value);
                    break;
                case "stock":
                    existingPlat.setStock((Integer) value);
                    break;
                case "photo":
                    existingPlat.setPhoto((String) value);
                    break;
                case "idRestaurant":
                    if (value instanceof Map<?, ?> restaurantMap) {
                        Integer restaurantId = (Integer) restaurantMap.get("id");
                        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + restaurantId + " not found"));
                        existingPlat.setIdRestaurant(restaurant);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return platRepository.save(existingPlat);
    }
}

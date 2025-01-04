package group.pant.api.service;

import group.pant.api.model.Plat;
import group.pant.api.model.Restaurant;
import group.pant.api.repository.PlatRepository;
import group.pant.api.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlatService {
    @Autowired
    private PlatRepository platRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Plat getPlatById(int id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + id + " not found"));
    }

    public Plat addPlat(Plat plat) {
        platRepository.save(plat);
        return plat;
    }

    public String deletePlat(int id) {
        platRepository.deleteById(id);
        return "Deleted Plat";
    }

    public Plat updatePlat(int id, Plat plat) {
        plat.setId(id);
        return platRepository.save(plat);
    }

    public Plat patchPlat(int id, Map<String, Object> patch) {
        Plat existingPlat = platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plat not found"));

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
                        Object restaurantIdValue = restaurantMap.get("id");
                        if (restaurantIdValue instanceof Integer restaurantId) { // Check if it is an Integer
                            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                                    .orElseThrow(() -> new RuntimeException("Restaurant not found"));
                            existingPlat.setIdRestaurant(restaurant);
                        } else {
                            throw new IllegalArgumentException("Invalid restaurant ID type");
                        }
                    }
                    break;
            }
        });

        return platRepository.save(existingPlat);
    }
}

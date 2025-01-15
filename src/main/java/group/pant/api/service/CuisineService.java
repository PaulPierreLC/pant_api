package group.pant.api.service;

import group.pant.api.model.Cuisine;
import group.pant.api.repository.CuisineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CuisineService {
    private final CuisineRepository cuisineRepository;

    public List<Cuisine> getAllCuisines() {
        return cuisineRepository.findAll();
    }

    public Cuisine getCuisineById(int id) {
        return cuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuisine with id " + id + " not found"));
    }

    public Cuisine addCuisine(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public void deleteCuisine(int id) {
        cuisineRepository.deleteById(id);
    }

    public Cuisine updateCuisine(int id, Cuisine cuisine) {
        Cuisine existingCuisine = cuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuisine with id " + id + " not found"));

        existingCuisine.setNom(cuisine.getNom());

        return cuisineRepository.save(existingCuisine);
    }

    public Cuisine patchCuisine(int id, Map<String, Object> patch) {
        Cuisine existingCuisine = cuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuisine with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingCuisine.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return cuisineRepository.save(existingCuisine);
    }
}

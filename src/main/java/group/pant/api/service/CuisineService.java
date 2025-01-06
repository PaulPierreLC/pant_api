package group.pant.api.service;

import group.pant.api.model.Cuisine;
import group.pant.api.repository.CuisineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CuisineService {
    @Autowired
    private CuisineRepository cuisineRepository;

    public List<Cuisine> getAllCuisines() {
        return cuisineRepository.findAll();
    }

    public Cuisine getCuisineById(int id) {
        return cuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuisine with id " + id + " not found"));
    }

    public Cuisine addCuisine(Cuisine cuisine) {
        cuisineRepository.save(cuisine);
        return cuisine;
    }

    public String deleteCuisine(int id) {
        cuisineRepository.deleteById(id);
        return "Deleted Cuisine";
    }

    public Cuisine updateCuisine(int id, Cuisine cuisine) {
        cuisine.setId(id);
        return cuisineRepository.save(cuisine);
    }

    public Cuisine patchCuisine(int id, Map<String, Object> patch) {
        Cuisine existingCuisine = cuisineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuisine not found"));

        patch.forEach((key, value) -> {
            if (key.equals("nom")) {
                existingCuisine.setNom((String) value);
            } else {
                throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return cuisineRepository.save(existingCuisine);
    }

}

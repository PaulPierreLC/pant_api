package group.pant.api.service;

import group.pant.api.model.PlatCuisine;
import group.pant.api.model.Plat;
import group.pant.api.model.Cuisine;
import group.pant.api.repository.PlatCuisineRepository;
import group.pant.api.repository.PlatRepository;
import group.pant.api.repository.CuisineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlatCuisineService {
    private final PlatCuisineRepository platCuisineRepository;
    private final PlatRepository platRepository;
    private final CuisineRepository cuisineRepository;

    public List<PlatCuisine> getAllPlatCuisines() {
        return platCuisineRepository.findAll();
    }

    public PlatCuisine getPlatCuisineById(int id) {
        return platCuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("platCuisine with id " + id + " not found"));
    }

    public PlatCuisine addPlatCuisine(PlatCuisine platCuisine) {
        platCuisineRepository.save(platCuisine);
        return platCuisine;
    }

    public void deletePlatCuisine(int id) {
        platCuisineRepository.deleteById(id);
    }

    public PlatCuisine updatePlatCuisine(int id, PlatCuisine platCuisine) {
        PlatCuisine existingPlatCuisine = platCuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("platCuisine with id " + id + " not found"));

        existingPlatCuisine.setIdPlat(platCuisine.getIdPlat());
        existingPlatCuisine.setIdCuisine(platCuisine.getIdCuisine());

        return platCuisineRepository.save(existingPlatCuisine);
    }

    public PlatCuisine patchPlatCuisine(int id, Map<String, Object> patch) {
        PlatCuisine existingPlatCuisine = platCuisineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("platCuisine with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idPlat":
                    if (value instanceof Map<?, ?> platMap) {
                        Integer platId = (Integer) platMap.get("id");
                        Plat plat = platRepository.findById(platId)
                                .orElseThrow(() -> new EntityNotFoundException("plat with id " + platId + " not found"));
                        existingPlatCuisine.setIdPlat(plat);
                    }
                    break;
                case "idCuisine":
                    if (value instanceof Map<?, ?> cuisineMap) {
                        Integer cuisineId = (Integer) cuisineMap.get("id");
                        Cuisine cuisine = cuisineRepository.findById(cuisineId)
                                .orElseThrow(() -> new EntityNotFoundException("cuisine with id " + cuisineId + " not found"));
                        existingPlatCuisine.setIdCuisine(cuisine);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return platCuisineRepository.save(existingPlatCuisine);
    }
}

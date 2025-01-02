package group.pant.api.service;

import group.pant.api.model.Ville;
import group.pant.api.repository.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class VilleService {
    @Autowired
    private VilleRepository VilleRepository;

    public List<Ville> getAllVilles() {
        return VilleRepository.findAll();
    }

    public Ville getVilleById(int id) {
        return VilleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ville with id " + id + " not found"));
    }

    public Ville addVille(Ville ville) {
        VilleRepository.save(ville);
        return ville;
    }

    public String deleteVilleById(int id) {
        VilleRepository.deleteById(id);
        return "Delete Plat";
    }

    public Ville updateVille(int id, Ville ville) {
        ville.setId(id);
        return VilleRepository.save(ville);
    }

    public Ville patchVille(int id, Map<String, Object> patch) {
        Ville existingVille = VilleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville not found"));

        patch.forEach((String key, Object value) -> {
            switch (key) {
                case "nom":
                    existingVille.setNom((String) value);
                    break;
                case "codePostal":
                    existingVille.setCodePostal((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid attribute: " + key);
            }
        });

        return VilleRepository.save(existingVille);
    }


}

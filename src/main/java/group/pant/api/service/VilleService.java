package group.pant.api.service;

import group.pant.api.model.Ville;
import group.pant.api.repository.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class VilleService {

    private final VilleRepository villeRepository;


    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    public Ville getVilleById(int id) {
        return villeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ville with id " + id + " not found"));
    }

    public Ville addVille(Ville ville) {
        villeRepository.save(ville);
        return ville;
    }

    public void deleteVille(int id) {
        villeRepository.deleteById(id);
    }

    public Ville updateVille(Integer id, Ville ville) {
        Ville existingVille = villeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ville with id " + id + " not found"));

        existingVille.setNom(ville.getNom());
        existingVille.setCodePostal(ville.getCodePostal());

        return villeRepository.save(existingVille);
    }

    public Ville patchVille(int id, Map<String, Object> patch) {
        Ville existingVille = villeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ville with id " + id + " not found"));

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

        return villeRepository.save(existingVille);
    }


}
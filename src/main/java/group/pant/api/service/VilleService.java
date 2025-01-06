package group.pant.api.service;

import group.pant.api.model.Ville;
import group.pant.api.repository.VilleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VilleService {

    private final VilleRepository villeRepository;

    // Injection par constructeur
    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    // Récupérer toutes les villes
    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    // Récupérer une ville par son ID
    public Optional<Ville> getVilleById(Integer id) {
        return villeRepository.findById(id);
    }

    // Créer une nouvelle ville
    public Ville createVille(Ville ville) {
        return villeRepository.save(ville);
    }

    // Mettre à jour une ville existante
    public Ville updateVille(Integer id, Ville villeDetails) {
        Ville ville = villeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ville not found"));
        ville.setNom(villeDetails.getNom());
        ville.setCodePostal(villeDetails.getCodePostal());
        return villeRepository.save(ville);
    }

    // Supprimer une ville
    public void deleteVille(Integer id) {
        Ville ville = villeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ville not found"));
        villeRepository.delete(ville);
    }

    // Mettre à jour partiellement une ville
    public Ville patchVille(Integer id, Map<String, Object> updates) {
        Ville ville = villeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ville not found"));

        updates.forEach((key, value) -> {
            if ("nom".equals(key)) {
                ville.setNom((String) value);
            } else if ("code_postal".equals(key)) {
                ville.setCodePostal((String) value);
            }
        });

        return villeRepository.save(ville);
    }
}

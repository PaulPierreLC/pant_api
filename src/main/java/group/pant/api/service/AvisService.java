package group.pant.api.service;

import group.pant.api.model.Avis;
import group.pant.api.repository.AvisRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvisService {

    private final AvisRepository avisRepository;

    // Récupérer toutes les avis
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    // Récupérer un Avis par son ID
    public Optional<Avis> getAvisById(Integer id) {
        return avisRepository.findById(id);
    }

    // Créer un nouvel avis
    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    // Mettre à jour un avis existant
    public Avis updateAvis(Integer id, Avis avisDetails) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found"));
        avis.setTitre(avisDetails.getTitre());  // Use setTitre instead of setNom
        avis.setDescription(avisDetails.getDescription());
        avis.setNote(avisDetails.getNote());
        return avisRepository.save(avis);
    }

    // Supprimer un avis
    public void deleteAvis(Integer id) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found"));
        avisRepository.delete(avis);
    }

    // Mettre à jour partiellement un avis
    public Avis patchAvis(Integer id, Map<String, Object> updates) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found"));

        updates.forEach((key, value) -> {
            if ("titre".equals(key)) {
                avis.setTitre((String) value);  // Use setTitre instead of setNom
            } else if ("description".equals(key)) {
                avis.setDescription((String) value);
            } else if ("note".equals(key)) {
                avis.setNote((Integer) value);
            }
        });

        return avisRepository.save(avis);
    }
}

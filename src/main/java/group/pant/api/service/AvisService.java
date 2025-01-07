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

    // Injection par constructeur
    public AvisService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    // Récupérer toutes les avis
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    // Récupérer une Avis par son ID
    public Optional<Avis> getAvisById(Integer id) {
        return avisRepository.findById(id);
    }

    // Créer une nouvelle avis
    public Avis createavis(Avis avis) {
        return avisRepository.save(avis);
    }

    // Mettre à jour une avis existante
    public Avis updateavis(Integer id, Avis avisDetails) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found"));
        avis.setNom(avisDetails.getNom());
        return avisRepository.save(avis);
    }

    // Supprimer une avis
    public void deleteavis(Integer id) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found"));
        avisRepository.delete(avis);
    }

    // Mettre à jour partiellement une avis
    public Avis patchavis(Integer id, Map<String, Object> updates) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis not found"));

        updates.forEach((key, value) -> {
            if ("nom".equals(key)) {
                avis.setNom((String) value);
            }
        });

        return avisRepository.save(avis);
    }
}

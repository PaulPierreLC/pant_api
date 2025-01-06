package group.pant.api.service;

import group.pant.api.model.Statut;
import group.pant.api.repository.StatutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StatutService {

    private final StatutRepository statutRepository;

    // Injection par constructeur
    public StatutService(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
    }

    // Récupérer tous les statuts
    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }

    // Récupérer un statut par son ID
    public Optional<Statut> getStatutById(Integer id) {
        return statutRepository.findById(id);
    }

    // Créer un nouveau statut
    public Statut createStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    // Mettre à jour un statut existant
    public Statut updateStatut(Integer id, Statut statutDetails) {
        Statut statut = statutRepository.findById(id).orElseThrow(() -> new RuntimeException("Statut not found"));
        statut.setNom(statutDetails.getNom());
        return statutRepository.save(statut);
    }

    // Supprimer un statut
    public void deleteStatut(Integer id) {
        Statut statut = statutRepository.findById(id).orElseThrow(() -> new RuntimeException("Statut not found"));
        statutRepository.delete(statut);
    }

    // Mettre à jour partiellement un statut
    public Statut patchStatut(Integer id, Map<String, Object> updates) {
        Statut statut = statutRepository.findById(id).orElseThrow(() -> new RuntimeException("Statut not found"));

        updates.forEach((key, value) -> {
            if ("nom".equals(key)) {
                statut.setNom((String) value);
            }
        });

        return statutRepository.save(statut);
    }
}

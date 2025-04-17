package group.pant.api.service;

import group.pant.api.model.Statut;
import group.pant.api.repository.StatutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatutService {

    private final StatutRepository statutRepository;

    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }

    public Statut getStatutById(Integer id) {
        return statutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statut with id " + id + " not found"));
    }

    public Statut addStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    public void deleteStatut(Integer id) {
        statutRepository.deleteById(id);
    }

    public Statut updateStatut(Integer id, Statut statut) {
        Statut existingStatut = statutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statut with id " + id + " not found"));
        existingStatut.setNom(statut.getNom());
        return statutRepository.save(existingStatut);
    }

    public Statut patchStatut(Integer id, Map<String, Object> patch) {
        Statut existingStatut = statutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Statut with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingStatut.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return statutRepository.save(existingStatut);
    }
}

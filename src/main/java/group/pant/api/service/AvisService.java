package group.pant.api.service;

import group.pant.api.model.Avis;
import group.pant.api.repository.AvisRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AvisService {

    private final AvisRepository avisRepository;

    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    public Avis getAvisById(Integer id) {
        return avisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avis with id " + id + " not found"));
    }

    public Avis addAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    public void deleteAvis(Integer id) {
        avisRepository.deleteById(id);
    }

    public Avis updateAvis(Integer id, Avis avis) {
        Avis existingAvis = avisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avis with id " + id + " not found"));

        existingAvis.setTitre(avis.getTitre());
        existingAvis.setDescription(avis.getDescription());
        existingAvis.setNote(avis.getNote());

        return avisRepository.save(existingAvis);
    }

    public Avis patchAvis(Integer id, Map<String, Object> patch) {
        Avis existingAvis = avisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avis with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "titre":
                    existingAvis.setTitre((String) value);
                    break;
                case "description":
                    existingAvis.setDescription((String) value);
                    break;
                case "note":
                    existingAvis.setNote((Integer) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return avisRepository.save(existingAvis);
    }
}

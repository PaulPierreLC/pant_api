package group.pant.api.service;

import group.pant.api.model.PaiementEtat;
import group.pant.api.repository.PaiementEtatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaiementEtatService {
    private final PaiementEtatRepository paiementEtatRepository;

    public List<PaiementEtat> getAllPaiementEtats() {
        return paiementEtatRepository.findAll();
    }

    public PaiementEtat getPaiementEtatById(int id) {
        return paiementEtatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaiementEtat with id " + id + " not found"));
    }

    public PaiementEtat addPaiementEtat(PaiementEtat paiementEtat) {
        paiementEtat.setDateCreer(Instant.now());
        paiementEtatRepository.save(paiementEtat);
        return paiementEtat;
    }

    public void deletePaiementEtat(int id) {
        paiementEtatRepository.deleteById(id);
    }

    public PaiementEtat updatePaiementEtat(int id, PaiementEtat paiementEtat) {
        PaiementEtat existingPaiementEtat = paiementEtatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaiementEtat with id " + id + " not found"));

        existingPaiementEtat.setId(paiementEtat.getId());
        existingPaiementEtat.setNom(paiementEtat.getNom());

        return paiementEtatRepository.save(existingPaiementEtat);
    }

    public PaiementEtat patchPaiementEtat(int id, Map<String, Object> patch) {
        PaiementEtat existingPaiementEtat = paiementEtatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaiementEtat with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingPaiementEtat.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return paiementEtatRepository.save(existingPaiementEtat);
    }
}


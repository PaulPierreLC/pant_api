package group.pant.api.service;

import group.pant.api.model.PaiementType;
import group.pant.api.repository.PaiementTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaiementTypeService {
    private final PaiementTypeRepository paiementTypeRepository;

    public List<PaiementType> getAllPaiementTypes() {
        return paiementTypeRepository.findAll();
    }

    public PaiementType getPaiementTypeById(int id) {
        return paiementTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaiementType with id " + id + " not found"));
    }

    public PaiementType addPaiementType(PaiementType paiementType) {
        paiementTypeRepository.save(paiementType);
        return paiementType;
    }

    public void deletePaiementType(int id) {
        paiementTypeRepository.deleteById(id);
    }

    public PaiementType updatePaiementType(int id, PaiementType paiementType) {
        PaiementType existingPaiementType = paiementTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaiementType with id " + id + " not found"));

        existingPaiementType.setNom(paiementType.getNom());

        return paiementTypeRepository.save(existingPaiementType);
    }

    public PaiementType patchPaiementType(int id, Map<String, Object> patch) {
        PaiementType existingPaiementType = paiementTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaiementType with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingPaiementType.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return paiementTypeRepository.save(existingPaiementType);
    }
}

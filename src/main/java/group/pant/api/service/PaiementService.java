package group.pant.api.service;

import group.pant.api.model.Paiement;
import group.pant.api.model.PaiementEtat;
import group.pant.api.model.PaiementType;
import group.pant.api.repository.PaiementEtatRepository;
import group.pant.api.repository.PaiementRepository;
import group.pant.api.repository.PaiementTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final PaiementTypeRepository paiementTypeRepository;
    private final PaiementEtatRepository paiementEtatRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Paiement getPaiementById(int id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement with id " + id + " not found"));
    }

    public Paiement addPaiement(Paiement paiement) {
        paiementRepository.save(paiement);
        return paiement;
    }

    public void deletePaiement(int id) {
        paiementRepository.deleteById(id);
    }

    public Paiement updatePaiement(int id, Paiement paiement) {
        Paiement existingPaiement = paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement with id " + id + " not found"));

        existingPaiement.setIdPaiementType(paiement.getIdPaiementType());
        existingPaiement.setIdPaiementEtat(paiement.getIdPaiementEtat());

        return paiementRepository.save(existingPaiement);
    }

    public Paiement patchPaiement(int id, Map<String, Object> patch) {
        Paiement existingPaiement = paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idPaiementType":
                    if (value instanceof Map<?, ?> paiementTypeMap) {
                        Integer paiementTypeId = (Integer) paiementTypeMap.get("id");
                        PaiementType paiementType = paiementTypeRepository.findById(paiementTypeId)
                                .orElseThrow(() -> new EntityNotFoundException("PaiementType with id " + paiementTypeId + " not found"));
                        existingPaiement.setIdPaiementType(paiementType);
                    }
                    break;
                case "idPaiementEtat":
                    if (value instanceof Map<?, ?> paiementEtatMap) {
                        Integer paiementEtatId = (Integer) paiementEtatMap.get("id");
                        PaiementEtat paiementEtat = paiementEtatRepository.findById(paiementEtatId)
                                .orElseThrow(() -> new EntityNotFoundException("PaiementEtat with id " + paiementEtatId + " not found"));
                        existingPaiement.setIdPaiementEtat(paiementEtat);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return paiementRepository.save(existingPaiement);
    }
}

package group.pant.api.service;

import group.pant.api.model.Vehicule;
import group.pant.api.model.VehiculeType;
import group.pant.api.repository.VehiculeRepository;
import group.pant.api.repository.VehiculeTypeRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeTypeRepository vehiculeTypeRepository;

    // Récupérer tous les véhicules
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    // Récupérer un véhicule par son ID
    public Optional<Vehicule> getVehiculeById(Integer id) {
        return vehiculeRepository.findById(id);
    }

    // Créer un nouveau véhicule
    public Vehicule createVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    // Mettre à jour un véhicule
    public Vehicule updateVehicule(Integer id, Vehicule vehiculeDetails) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        vehicule.setNom(vehiculeDetails.getNom());

        if (vehiculeDetails.getIdVehiculeType() != null) {
            VehiculeType vehiculeType = vehiculeTypeRepository.findById(vehiculeDetails.getIdVehiculeType().getId())
                    .orElseThrow(() -> new RuntimeException("Type de véhicule introuvable"));
            vehicule.setIdVehiculeType(vehiculeType);
        }

        return vehiculeRepository.save(vehicule);
    }

    // Supprimer un véhicule
    public String deleteVehicule(Integer id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));
        vehiculeRepository.delete(vehicule);
        return "Véhicule supprimé avec succès.";
    }


    // Mise à jour partielle (PATCH)
    public Vehicule patchVehicule(Integer id, Map<String, Object> updates) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    vehicule.setNom((String) value);
                    break;
                case "idVehiculeType":
                    // Convert Integer to VehiculeType
                    VehiculeType vehiculeType = vehiculeTypeRepository.findById((Integer) value)
                            .orElseThrow(() -> new RuntimeException("Type de véhicule introuvable"));
                    vehicule.setIdVehiculeType(vehiculeType);
                    break;
                default:
                    throw new RuntimeException("Propriété non prise en charge : " + key);
            }
        });

        return vehiculeRepository.save(vehicule);
    }
}

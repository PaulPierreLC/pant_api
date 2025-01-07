package group.pant.api.service;

import group.pant.api.model.VehiculeType;
import group.pant.api.repository.VehiculeTypeRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeTypeService {

    private final VehiculeTypeRepository vehiculeTypeRepository;

    // Récupérer tous les types de véhicules
    public List<VehiculeType> getAllVehiculeTypes() {
        return vehiculeTypeRepository.findAll();
    }

    // Récupérer un type de véhicule par son id
    public Optional<VehiculeType> getVehiculeTypeById(Integer id) {
        return vehiculeTypeRepository.findById(id);
    }

    // Créer un nouveau type de véhicule
    public VehiculeType createVehiculeType(VehiculeType vehiculeType) {
        return vehiculeTypeRepository.save(vehiculeType);
    }

    // Mettre à jour un type de véhicule existant
    public VehiculeType updateVehiculeType(Integer id, VehiculeType vehiculeTypeDetails) {
        VehiculeType vehiculeType = vehiculeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VehiculeType not found"));
        vehiculeType.setNom(vehiculeTypeDetails.getNom());
        return vehiculeTypeRepository.save(vehiculeType);
    }

    // Supprimer un type de véhicule
    public void deleteVehiculeType(Integer id) {
        VehiculeType vehiculeType = vehiculeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VehiculeType not found"));
        vehiculeTypeRepository.delete(vehiculeType);
    }

    // Mettre à jour partiellement un type de véhicule
    public VehiculeType patchVehiculeType(Integer id, Map<String, Object> updates) {
        VehiculeType vehiculeType = vehiculeTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VehiculeType not found"));

        updates.forEach((key, value) -> {
            if ("nom".equals(key)) {
                vehiculeType.setNom((String) value);
            }
        });

        return vehiculeTypeRepository.save(vehiculeType);
    }
}

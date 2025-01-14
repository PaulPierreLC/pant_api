package group.pant.api.service;

import group.pant.api.model.VehiculeType;
import group.pant.api.repository.VehiculeTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VehiculeTypeService {

    private final VehiculeTypeRepository vehiculeTypeRepository;

    public List<VehiculeType> getAllVehiculeTypes() {
        return vehiculeTypeRepository.findAll();
    }

    public VehiculeType getVehiculeTypeById(Integer id) {
        return vehiculeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehiculeType with id " + id + " not found"));
    }

    public VehiculeType addVehiculeType(VehiculeType vehiculeType) {
        return vehiculeTypeRepository.save(vehiculeType);
    }

    public void deleteVehiculeType(Integer id) {
        vehiculeTypeRepository.deleteById(id);
    }

    public VehiculeType updateVehiculeType(Integer id, VehiculeType vehiculeType) {
        VehiculeType existingVehiculeType = vehiculeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehiculeType with id " + id + " not found"));
        existingVehiculeType.setNom(vehiculeType.getNom());
        return vehiculeTypeRepository.save(existingVehiculeType);
    }

    public VehiculeType patchVehiculeType(Integer id, Map<String, Object> patch) {
        VehiculeType existingVehiculeType = vehiculeTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehiculeType with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingVehiculeType.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return vehiculeTypeRepository.save(existingVehiculeType);
    }
}

package group.pant.api.service;

import group.pant.api.model.Vehicule;
import group.pant.api.model.VehiculeType;
import group.pant.api.repository.VehiculeRepository;
import group.pant.api.repository.VehiculeTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeTypeRepository vehiculeTypeRepository;

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Vehicule getVehiculeById(Integer id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule with id " + id + " not found"));
    }

    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteVehicule(Integer id) {
        vehiculeRepository.deleteById(id);
    }

    public Vehicule updateVehicule(Integer id, Vehicule vehicule) {
        Vehicule existingVehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule with id " + id + " not found"));

        existingVehicule.setNom(vehicule.getNom());
        existingVehicule.setIdVehiculeType(vehicule.getIdVehiculeType());

        return vehiculeRepository.save(existingVehicule);
    }

    public Vehicule patchVehicule(Integer id, Map<String, Object> patch) {
        Vehicule existingVehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingVehicule.setNom((String) value);
                    break;
                case "idVehiculeType":
                    if (value instanceof Map<?, ?> vehiculeTypeMap) {
                        Integer vehiculeTypeId = (Integer) vehiculeTypeMap.get("id");
                        VehiculeType vehiculeType = vehiculeTypeRepository.findById(vehiculeTypeId)
                                .orElseThrow(() -> new EntityNotFoundException("VehiculeType with id " + vehiculeTypeId + " not found"));
                        existingVehicule.setIdVehiculeType(vehiculeType);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return vehiculeRepository.save(existingVehicule);
    }
}

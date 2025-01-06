package group.pant.api.service;

import group.pant.api.model.Vehicule;
import group.pant.api.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    // Injection par constructeur
    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

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
        vehicule.setIdVehiculeType(vehiculeDetails.getIdVehiculeType());
        
        return vehiculeRepository.save(vehicule);
    }

    // Supprimer un véhicule
    public void deleteVehicule(Integer id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));
        
        vehiculeRepository.delete(vehicule);
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
                    vehicule.setIdVehiculeType((Integer) value);
                    break;
                default:
                    throw new RuntimeException("Propriété non prise en charge : " + key);
            }
        });

        return vehiculeRepository.save(vehicule);
    }
}

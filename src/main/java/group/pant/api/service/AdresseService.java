package group.pant.api.service;

import group.pant.api.model.Adresse;
import group.pant.api.repository.AdresseRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdresseService {

    private final AdresseRepository adresseRepository;

    // Injection par constructeur
    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    // Récupérer toutes les adresses
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    // Récupérer une adresse par son ID
    public Optional<Adresse> getAdresseById(Integer id) {
        return adresseRepository.findById(id);
    }

    // Créer une nouvelle adresse
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    // Mettre à jour une adresse existante
    public Adresse updateAdresse(Integer id, Adresse adresseDetails) {
        Adresse adresse = adresseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse not found"));
        adresse.setNumero(adresseDetails.getNumero());
        adresse.setRue(adresseDetails.getRue());
        adresse.setComplement(adresseDetails.getComplement());
        adresse.setLongitude(adresseDetails.getLongitude());
        adresse.setLatitude(adresseDetails.getLatitude());
        adresse.setIdVille(adresseDetails.getIdVille());
        return adresseRepository.save(adresse);
    }

    // Supprimer une adresse
    public void deleteAdresse(Integer id) {
        Adresse adresse = adresseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse not found"));
        adresseRepository.delete(adresse);
    }

    // Mettre à jour partiellement une adresse
    public Adresse patchAdresse(Integer id, Map<String, Object> updates) {
        Adresse adresse = adresseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adresse not found"));

        updates.forEach((key, value) -> {
            if ("numero".equals(key)) {
                adresse.setNumero((String) value);
            } else if ("rue".equals(key)) {
                adresse.setRue((String) value);
            } else if ("complement".equals(key)) {
                adresse.setComplement((String) value);
            } else if ("longitude".equals(key)) {
                adresse.setLongitude((Double) value);
            } else if ("latitude".equals(key)) {
                adresse.setLatitude((Double) value);
            } else if ("idVille".equals(key)) {
                adresse.setIdVille((Integer) value);
            }
        });

        return adresseRepository.save(adresse);
    }
}

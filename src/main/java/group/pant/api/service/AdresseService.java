package group.pant.api.service;

import group.pant.api.model.Adresse;
import group.pant.api.repository.AdresseRepository;
import group.pant.api.model.Ville;
import group.pant.api.repository.VilleRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdresseService {

    private final AdresseRepository adresseRepository;
    private final VilleRepository villeRepository;

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

        if (adresseDetails.getVille() != null && adresseDetails.getVille().getId() != null) {
            Ville ville = villeRepository.findById(adresseDetails.getVille().getId())
                    .orElseThrow(() -> new RuntimeException("Ville not found"));
            adresse.setVille(ville);
        } else {
            throw new IllegalArgumentException("Ville ID cannot be null");
        }

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
            } else if ("ville".equals(key)) {
                if (value instanceof Map<?, ?> villeMap) {
                    Object villeIdValue = villeMap.get("id");
                    if (villeIdValue instanceof Integer villeId) {
                        Ville ville = villeRepository.findById(villeId)
                                .orElseThrow(() -> new RuntimeException("Ville not found"));
                        adresse.setVille(ville);
                    } else {
                        throw new IllegalArgumentException("Invalid ville ID type");
                    }
                }
            }
        });

        return adresseRepository.save(adresse);
    }
}
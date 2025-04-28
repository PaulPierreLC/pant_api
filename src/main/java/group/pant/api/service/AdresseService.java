package group.pant.api.service;

import group.pant.api.dto.AdresseDto;
import group.pant.api.model.*;
import group.pant.api.repository.AdresseRepository;
import group.pant.api.repository.VilleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdresseService {
    private final AdresseRepository adresseRepository;
    private final VilleRepository villeRepository;

    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    public Adresse getAdresseById(Integer id) {
        return adresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + id + " not found"));
    }

    public Adresse addAdresse(AdresseDto adresseDto) {
        Adresse adresse = new Adresse();

        Ville ville = villeRepository.findById(adresseDto.getIdVille())
                .orElseThrow(() -> new RuntimeException("Ville not found"));
        adresse.setIdVille(ville);

        adresse.setNumero(adresseDto.getNumero());
        adresse.setRue(adresseDto.getRue());
        adresse.setComplement(adresseDto.getComplement());
        adresse.setLongitude(adresseDto.getLongitude());
        adresse.setLatitude(adresseDto.getLatitude());
        
        return adresseRepository.save(adresse);
    }

    public void deleteAdresse(Integer id) {
        adresseRepository.deleteById(id);
    }

    public Adresse updateAdresse(Integer id, Adresse adresse) {
        Adresse existingAdresse = adresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + id + " not found"));

        existingAdresse.setNumero(adresse.getNumero());
        existingAdresse.setRue(adresse.getRue());
        existingAdresse.setComplement(adresse.getComplement());
        existingAdresse.setLongitude(adresse.getLongitude());
        existingAdresse.setLatitude(adresse.getLatitude());
        existingAdresse.setIdVille(adresse.getIdVille());

        return adresseRepository.save(existingAdresse);
    }

    public Adresse patchAdresse(Integer id, Map<String, Object> patch) {
        Adresse existingAdresse = adresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Adresse with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "numero":
                    existingAdresse.setNumero((String) value);
                    break;
                case "rue":
                    existingAdresse.setRue((String) value);
                    break;
                case "complement":
                    existingAdresse.setComplement((String) value);
                    break;
                case "longitude":
                    existingAdresse.setLongitude((Double) value);
                    break;
                case "latitude":
                    existingAdresse.setLatitude((Double) value);
                    break;
                case "idVille":
                    if (value instanceof Map<?, ?> villeMap) {
                        Integer villeId = (Integer) villeMap.get("id");
                        Ville ville = villeRepository.findById(villeId)
                                .orElseThrow(() -> new EntityNotFoundException("Ville with id " + villeId + " not found"));
                        existingAdresse.setIdVille(ville);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });


        return adresseRepository.save(existingAdresse);
    }
}

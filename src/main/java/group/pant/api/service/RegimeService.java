package group.pant.api.service;

import group.pant.api.model.Regime;
import group.pant.api.repository.RegimeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegimeService {
    private final RegimeRepository regimeRepository;

    public List<Regime> getAllRegimes() {
        return regimeRepository.findAll();
    }

    public Regime getRegimeById(int id) {
        return regimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regime with id " + id + " not found"));
    }

    public Regime addRegime(Regime regime) {
        regimeRepository.save(regime);
        return regime;
    }

    public void deleteRegime(int id) {
        regimeRepository.deleteById(id);
    }

    public Regime updateRegime(int id, Regime regime) {
        Regime existingRegime = regimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regime with id " + id + " not found"));
        existingRegime.setNom(regime.getNom());
        return regimeRepository.save(existingRegime);
    }

    public Regime patchRegime(int id, Map<String, Object> patch) {
        Regime existingRegime = regimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Regime with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "nom":
                    existingRegime.setNom((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return regimeRepository.save(existingRegime);
    }
}

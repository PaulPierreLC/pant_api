package group.pant.api.service;

import group.pant.api.model.PlatRegime;
import group.pant.api.model.Plat;
import group.pant.api.model.Regime;
import group.pant.api.repository.PlatRegimeRepository;
import group.pant.api.repository.PlatRepository;
import group.pant.api.repository.RegimeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlatRegimeService {
    private final PlatRegimeRepository platRegimeRepository;
    private final PlatRepository platRepository;
    private final RegimeRepository regimeRepository;

    public List<PlatRegime> getAllPlatRegimes() {
        return platRegimeRepository.findAll();
    }

    public PlatRegime getPlatRegimeById(int id) {
        return platRegimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PlatRegime with id " + id + " not found"));
    }

    public PlatRegime addPlatRegime(PlatRegime platRegime) {
        platRegimeRepository.save(platRegime);
        return platRegime;
    }

    public void deletePlatRegime(int id) {
        platRegimeRepository.deleteById(id);
    }

    public PlatRegime updatePlatRegime(int id, PlatRegime platRegime) {
        PlatRegime existingPlatRegime = platRegimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PlatRegime with id " + id + " not found"));

        existingPlatRegime.setIdPlat(platRegime.getIdPlat());
        existingPlatRegime.setIdRegime(platRegime.getIdRegime());

        return platRegimeRepository.save(existingPlatRegime);
    }

    public PlatRegime patchPlatRegime(int id, Map<String, Object> patch) {
        PlatRegime existingPlatRegime = platRegimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PlatRegime with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idPlat":
                    if (value instanceof Map<?, ?> platMap) {
                        Integer platId = (Integer) platMap.get("id");
                        Plat plat = platRepository.findById(platId)
                                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + platId + " not found"));
                        existingPlatRegime.setIdPlat(plat);
                    }
                    break;
                case "idRegime":
                    if (value instanceof Map<?, ?> regimeMap) {
                        Integer regimeId = (Integer) regimeMap.get("id");
                        Regime regime = regimeRepository.findById(regimeId)
                                .orElseThrow(() -> new EntityNotFoundException("Regime with id " + regimeId + " not found"));
                        existingPlatRegime.setIdRegime(regime);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return platRegimeRepository.save(existingPlatRegime);
    }
}

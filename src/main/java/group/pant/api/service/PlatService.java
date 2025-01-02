package group.pant.api.service;

import group.pant.api.model.Plat;
import group.pant.api.repository.PlatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatService {
    @Autowired
    private PlatRepository platRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Plat getPlatById(int id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plat with id " + id + " not found"));
    }

}

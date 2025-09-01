package group.pant.api.service;

import group.pant.api.model.UtilisateurAdresse;
import group.pant.api.repository.UtilisateurAdresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurAdresseService {
    private final UtilisateurAdresseRepository utilisateurAdresseRepository;

    public List<UtilisateurAdresse> getAllUtilisateurAdresses() {
        return utilisateurAdresseRepository.findAll();
    }

    public UtilisateurAdresse addUtilisateurAdresse(UtilisateurAdresse utilisateurAdresse) {
        return utilisateurAdresseRepository.save(utilisateurAdresse);
    }

    public void deleteUtilisateurAdresse(Integer id) {
        utilisateurAdresseRepository.deleteById(id);
    }
}

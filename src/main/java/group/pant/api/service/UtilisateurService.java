package group.pant.api.service;

import group.pant.api.model.Utilisateur;
import group.pant.api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id).get();
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }
}
package group.pant.api.service;

import group.pant.api.model.Utilisateur;
import group.pant.api.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur with id " + id + " not found"));
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(int id, Utilisateur newData) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur with id " + id + " not found"));

        existingUtilisateur.setPrenom(newData.getPrenom());
        existingUtilisateur.setNom(newData.getNom());
        existingUtilisateur.setTelMobile(newData.getTelMobile());
        existingUtilisateur.setTelFix(newData.getTelFix());
        existingUtilisateur.setMail(newData.getMail());
        existingUtilisateur.setParametre(newData.getParametre());
        existingUtilisateur.setPointsFidelite(newData.getPointsFidelite());
        existingUtilisateur.setDateCreation(newData.getDateCreation());
        existingUtilisateur.setIdRole(newData.getIdRole());
        existingUtilisateur.setIdVehicule(newData.getIdVehicule());

        return utilisateurRepository.save(existingUtilisateur);
    }

    public Utilisateur patchUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }
}
package group.pant.api.service;

import group.pant.api.model.Role;
import group.pant.api.model.Utilisateur;
import group.pant.api.model.Vehicule;
import group.pant.api.repository.RoleRepository;
import group.pant.api.repository.UtilisateurRepository;
import group.pant.api.repository.VehiculeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final VehiculeRepository vehiculeRepository;

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

    public void deleteUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur updateUtilisateur(int id, Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur with id " + id + " not found"));

        existingUtilisateur.setPrenom(utilisateur.getPrenom());
        existingUtilisateur.setNom(utilisateur.getNom());
        existingUtilisateur.setTelMobile(utilisateur.getTelMobile());
        existingUtilisateur.setTelFix(utilisateur.getTelFix());
        existingUtilisateur.setMail(utilisateur.getMail());
        existingUtilisateur.setParametre(utilisateur.getParametre());
        existingUtilisateur.setPointsFidelite(utilisateur.getPointsFidelite());
        existingUtilisateur.setIdRole(utilisateur.getIdRole());
        existingUtilisateur.setIdVehicule(utilisateur.getIdVehicule());

        return utilisateurRepository.save(existingUtilisateur);
    }

    public Utilisateur patchUtilisateur(Integer id, Map<String, Object> patch) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "prenom":
                    existingUtilisateur.setPrenom((String) value);
                    break;
                case "nom":
                    existingUtilisateur.setNom((String) value);
                    break;
                case "telMobile":
                    existingUtilisateur.setTelMobile((String) value);
                    break;
                case "telFix":
                    existingUtilisateur.setTelFix((String) value);
                    break;
                case "mail":
                    existingUtilisateur.setMail((String) value);
                    break;
                case "parametre":
                    existingUtilisateur.setParametre((String) value);
                    break;
                case "pointsFidelite":
                    existingUtilisateur.setPointsFidelite((Integer) value);
                    break;
                case "idRole":
                    if (value instanceof Map<?, ?> roleMap) {
                        Integer roleId = (Integer) roleMap.get("id");
                        Role role = roleRepository.findById(roleId)
                                .orElseThrow(() -> new EntityNotFoundException("Role with id " + roleId + " not found"));
                        existingUtilisateur.setIdRole(role);
                    }
                    break;
                case "idVehicule":
                    if (value instanceof Map<?, ?> vehiculeMap) {
                        Integer vehiculeId = (Integer) vehiculeMap.get("id");
                        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                                .orElseThrow(() -> new EntityNotFoundException("Vehicule with id " + vehiculeId + " not found"));
                        existingUtilisateur.setIdVehicule(vehicule);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return utilisateurRepository.save(existingUtilisateur);
    }
}

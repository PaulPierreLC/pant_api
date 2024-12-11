package group.pant.api.repository;

import group.pant.api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByNom(String nom);
}
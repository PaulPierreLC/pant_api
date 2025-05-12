package group.pant.api.repository;

import group.pant.api.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
    Optional<Ville> findByNom(String nom);
}
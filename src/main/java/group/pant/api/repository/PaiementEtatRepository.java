package group.pant.api.repository;

import group.pant.api.model.PaiementEtat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementEtatRepository extends JpaRepository<PaiementEtat, Integer> {
}
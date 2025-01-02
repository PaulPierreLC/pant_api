package group.pant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import group.pant.api.model.Avis;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
}
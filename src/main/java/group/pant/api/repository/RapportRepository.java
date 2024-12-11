package group.pant.api.repository;

import group.pant.api.model.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepository extends JpaRepository<Rapport, Integer> {
}
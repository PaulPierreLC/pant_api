package group.pant.api.repository;

import group.pant.api.model.Regime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegimeRepository extends JpaRepository<Regime, Integer> {
}
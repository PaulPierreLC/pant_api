package group.pant.api.repository;

import group.pant.api.model.Plat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
}
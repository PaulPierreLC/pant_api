package group.pant.api.repository;

import group.pant.api.model.Avi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AviRepository extends JpaRepository<Avi, Integer> {
}
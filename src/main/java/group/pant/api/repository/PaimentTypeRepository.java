package group.pant.api.repository;

import group.pant.api.model.PaimentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaimentTypeRepository extends JpaRepository<PaimentType, Integer> {
}
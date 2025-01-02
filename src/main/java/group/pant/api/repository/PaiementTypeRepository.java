package group.pant.api.repository;

import group.pant.api.model.PaiementType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementTypeRepository extends JpaRepository<PaiementType, Integer> {
}
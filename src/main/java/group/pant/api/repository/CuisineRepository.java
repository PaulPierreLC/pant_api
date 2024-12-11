package group.pant.api.repository;

import group.pant.api.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {
}
package group.pant.api.repository;

import group.pant.api.model.Plat;
import group.pant.api.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
    List<Plat> findByIdRestaurant(Restaurant restaurant);
}
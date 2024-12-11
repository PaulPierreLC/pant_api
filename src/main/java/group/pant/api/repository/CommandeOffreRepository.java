package group.pant.api.repository;

import group.pant.api.model.CommandeOffre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeOffreRepository extends JpaRepository<CommandeOffre, Integer> {
}
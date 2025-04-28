package group.pant.api.repository;

import group.pant.api.model.Commande;
import group.pant.api.model.CommandeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeDetailRepository extends JpaRepository<CommandeDetail, Integer> {
    List<CommandeDetail> findByIdCommande(Commande commande);
}
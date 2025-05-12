package group.pant.api.repository;

import group.pant.api.model.Commande;
import group.pant.api.model.CommandeStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeStatutRepository extends JpaRepository<CommandeStatut, Integer> {
    List<CommandeStatut> findByIdCommande(Commande commande);
}
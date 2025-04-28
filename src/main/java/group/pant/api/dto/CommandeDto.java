package group.pant.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link group.pant.api.model.Commande}
 */
@Data
public class CommandeDto implements Serializable {
    Integer idPaiement;
    Integer idUtilisateurClient;
    Integer idAdresse;
    String heure;
}
package group.pant.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link group.pant.api.model.CommandeStatut}
 */
@Data
public class CommandeStatutDto implements Serializable {
    Integer idCommande;
    Integer idStatut;
}
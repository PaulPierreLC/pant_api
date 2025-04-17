package group.pant.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link group.pant.api.model.CommandeDetail}
 */
@Data
public class CommandeDetailDto implements Serializable {
    Integer idCommande;
    Integer idPlat;
    Integer quantite;
}
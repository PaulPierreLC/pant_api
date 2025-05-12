package group.pant.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link group.pant.api.model.Adresse}
 */
@Data
public class AdresseDto implements Serializable {
    String numero;
    String rue;
    String complement;
    Double longitude;
    Double latitude;
    Integer idVille;
}
package group.pant.api.dto;

import group.pant.api.model.Adresse;
import group.pant.api.model.Utilisateur; // <-- Ajoute cet import
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurCreationDTO {
    private Utilisateur utilisateur;
    private Adresse adresseDomicile;
    private Adresse adresseTravail;
}
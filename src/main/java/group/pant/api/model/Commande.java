package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "commande")
public class Commande {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("current_timestamp()")
    @Column(name = "date_creation")
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paiement")
    private Paiement idPaiement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilisateur_client")
    private Utilisateur idUtilisateurClient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilisateur_livreur")
    private Utilisateur idUtilisateurLivreur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adresse")
    private Adresse idAdresse;

}
package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "avis")
public class Avi {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titre", length = 45)
    private String titre;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "note", nullable = false)
    private Integer note;

    @ColumnDefault("current_timestamp()")
    @Column(name = "date_creation")
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant idRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur idUtilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commande")
    private Commande idCommande;

}
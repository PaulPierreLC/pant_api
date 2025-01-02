package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "commande_statuts")
public class CommandeStatut {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_commande", nullable = false)
    private Commande idCommande;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_statut", nullable = false)
    private Statut idStatut;

    @ColumnDefault("current_timestamp()")
    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @Lob
    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "lattitude")
    private Double lattitude;

}
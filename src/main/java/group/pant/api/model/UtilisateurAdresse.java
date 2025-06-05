package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "utilisateur_adresses")
public class UtilisateurAdresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_adresse", nullable = false)
    private Adresse adresse;

    @Column(name = "defaut", nullable = false)
    private Boolean defaut;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AdresseType type;

    @CreationTimestamp
    @Column(name = "date_creer", updatable = false)
    private Instant dateCreer;

    @UpdateTimestamp
    @Column(name = "date_maj")
    private Instant dateMaj;

    public enum AdresseType {
        domicile, travail
    }
}
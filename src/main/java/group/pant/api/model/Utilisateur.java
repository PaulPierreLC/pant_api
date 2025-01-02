package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prenom", length = 45)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "tel_mobile", nullable = false, length = 45)
    private String telMobile;

    @Column(name = "tel_fix", length = 45)
    private String telFix;

    @Column(name = "mail", nullable = false, length = 45)
    private String mail;

    @Lob
    @Column(name = "parametre", nullable = false)
    private String parametre;

    @ColumnDefault("0")
    @Column(name = "points_fidelite", nullable = false)
    private Integer pointsFidelite;

    @ColumnDefault("current_timestamp()")
    @Column(name = "date_creation")
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private Role idRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule")
    private Vehicule idVehicule;

}
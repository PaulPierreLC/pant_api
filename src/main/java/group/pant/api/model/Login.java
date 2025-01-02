package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "id_utilisateur", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @Column(name = "mot_de_passe", nullable = false, length = 45)
    private String motDePasse;

}
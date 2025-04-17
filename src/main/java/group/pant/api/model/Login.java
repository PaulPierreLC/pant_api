package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @Column(name = "mot_de_passe", nullable = false, length = 45)
    private String motDePasse;

    @PrePersist
    public void ensureId() {
        if (utilisateur != null && id == null) {
            id = utilisateur.getId();
        }
    }
}
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
@Table(name = "paiement")
public class Paiement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_paiement_type")
    private PaiementType idPaiementType;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_paiement_etat")
    private PaiementEtat idPaiementEtat;

    @ColumnDefault("current_timestamp()")
    @CreationTimestamp
    @Column(name = "date_creer", updatable = false)

    private Instant dateCreer;

    @UpdateTimestamp
    @Column(name = "date_maj")
    private Instant dateMaj;

}
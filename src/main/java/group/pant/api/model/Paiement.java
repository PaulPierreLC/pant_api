package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "etat", nullable = false)
    private Boolean etat = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_methode_paiement")
    private PaiementType idMethodePaiement;

}
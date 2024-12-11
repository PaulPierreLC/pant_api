package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_methode_paiement", nullable = false)
    private PaimentType idMethodePaiement;

}
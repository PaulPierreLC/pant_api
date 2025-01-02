package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "plat")
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prix", nullable = false)
    private Float prix;

    @Column(name = "poids", nullable = false)
    private Float poids;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "photo", length = 45)
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant idRestaurant;

}
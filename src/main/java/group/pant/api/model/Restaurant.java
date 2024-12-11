package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "telephone", nullable = false, length = 45)
    private String telephone;

    @Column(name = "capacite", nullable = false)
    private Integer capacite;

    @Column(name = "photo", length = 45)
    private String photo;

    @ColumnDefault("current_timestamp()")
    @Column(name = "date_creation")
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_adresse", nullable = false)
    private Adresse idAdresse;

}
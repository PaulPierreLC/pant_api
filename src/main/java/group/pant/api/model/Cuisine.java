package group.pant.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cuisine")
public class Cuisine {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 45)
    private String nom;

    @ColumnDefault("current_timestamp()")
    @CreationTimestamp
    @Column(name = "date_creer", updatable = false)
    private Instant dateCreer;

    @UpdateTimestamp
    @Column(name = "date_maj")
    private Instant dateMaj;
}
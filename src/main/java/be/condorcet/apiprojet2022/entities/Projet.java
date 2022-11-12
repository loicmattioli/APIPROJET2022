package be.condorcet.apiprojet2022.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIPROJET", schema = "ora3", catalog = "orcl")
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "projet_generator")
    @SequenceGenerator(name="projet_generator", sequenceName =
            "APIPROJET_SEQ", allocationSize=1)
    private Integer id_apiprojet;
    @NonNull
    private String titre;
    @NonNull
    private Date dateDebut;
    @NonNull
    private Date dateFin;
    @NonNull
    private String cout;

    @ManyToOne
    @JoinColumn(name = "ID_APIDISCIPLINES")
    private Disciplines disciplines;
}

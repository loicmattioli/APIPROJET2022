/*package be.condorcet.apiprojet2022.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APICOMPETENCE", schema = "ora3", catalog = "orcl")
public class Competence {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "competence_generator")
    @SequenceGenerator(name="competence_generator", sequenceName =
            "APICOMPETENCE_SEQ", allocationSize=1)

    @JsonIgnoreProperties("apicompetence")
    @ManyToOne
    @JoinColumn(name = "ID_APIEMPLOYE")
    private Employe employe;
    @JsonIgnoreProperties("apicompetence")
    @ManyToOne
    @JoinColumn(name = "ID_APIDISCIPLINES")
    private Disciplines disciplines;
    @NonNull
    private Integer niveau;

}*/

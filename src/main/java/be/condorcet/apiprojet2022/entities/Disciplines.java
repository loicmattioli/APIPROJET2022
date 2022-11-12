package be.condorcet.apiprojet2022.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIDISCIPLINES", schema = "ora3", catalog = "orcl")
public class Disciplines {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "disciplines_generator")
    @SequenceGenerator(name="disciplines_generator", sequenceName =
            "APIDISCIPLINES_SEQ", allocationSize=1)
    private Integer id_apidisciplines;
    @NonNull
    private String nom;
    private String description;

    @OneToMany(mappedBy = "disciplines")
    @ToString.Exclude
    @JsonIgnore
    private List<Projet> projets;
}

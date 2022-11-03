package be.condorcet.apiprojet2022.entities;


import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;

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
    @NonNull
    private String description;
}
